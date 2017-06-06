package example.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import example.dto.Emp;
import example.dto.EmpDept;
import example.service.EmpService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Matchers.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author tsasaki
 */
public class EmpResourceTest extends JerseyTest{
    @Mock
    EmpService service;

    @Override
    protected Application configure() {
        MockitoAnnotations.initMocks(this);

        return new MyApp().register(new AbstractBinder() {
            @Override
            protected void configure() {
                this.bindFactory(new Factory<EmpService>(){
                    @Override
                    public EmpService provide() {
                        return service;
                    }
                    @Override
                    public void dispose(EmpService instance) {
                    }
                }).to(EmpService.class);

                this.bindFactory(new Factory<ObjectMapper>(){
                    @Override
                    public ObjectMapper provide() {
                        return new ObjectMapper();
                    }
                    @Override
                    public void dispose(ObjectMapper instance) {
                    }
                }).to(ObjectMapper.class);
            }
        });
    }

    

    @Test
    public void empの一覧取得() throws Exception {
        when(service.getEmpList()).thenReturn(Arrays.asList(
            new Emp(100, "ename", LocalDate.of(2017, 1, 2), 100),
            new Emp(101, "ename2", LocalDate.of(2017, 1, 3), 100)
        ));

        ObjectNode res = target("emps")
                .request(MediaType.APPLICATION_JSON)
                .get(ObjectNode.class);

        assertThat(res.get("emps").size(), is(2));
        JsonNode emp = res.get("emps").get(0);
        assertThat(emp.get("empno").asInt(), is(100));
        assertThat(emp.get("ename").asText(), is("ename"));
        assertThat(emp.get("hiredate").asText(), is("2017/01/02"));
        assertThat(emp.get("deptno").asInt(), is(100));
    }

    @Test
    public void ename付きempの一覧取得() throws Exception {
        when(service.getEmpDeptList()).thenReturn(Arrays.asList(
            new EmpDept(100, "ename", LocalDate.of(2017, 1, 2), 100, "deptname"),
            new EmpDept(101, "ename2", LocalDate.of(2017, 1, 3), null, null)
        ));

        ObjectNode res = target("emps/with-dname")
                .request(MediaType.APPLICATION_JSON)
                .get(ObjectNode.class);

        assertThat(res.get("emps").size(), is(2));
        JsonNode emp = res.get("emps").get(0);
        assertThat(emp.get("empno").asInt(), is(100));
        assertThat(emp.get("ename").asText(), is("ename"));
        assertThat(emp.get("hiredate").asText(), is("2017/01/02"));
        assertThat(emp.get("deptno").asInt(), is(100));
        assertThat(emp.get("dname").asText(), is("deptname"));
        assertThat(res.get("emps").get(1).get("deptno").isNull(), is(true));
        assertThat(res.get("emps").get(1).get("dname").isNull(), is(true));
    }

    @Test
    public void empMapによる一覧取得() throws Exception {
        Map row = new HashMap();
        row.put("empno", 100);
        row.put("ename", "ename");

        when(service.getEmpDeptListByMap()).thenReturn(Arrays.asList(row));

        ObjectNode res = target("emps/with-dname-by-map")
                .request(MediaType.APPLICATION_JSON)
                .get(ObjectNode.class);

        assertThat(res.get("emps").size(), is(1));
        JsonNode emp = res.get("emps").get(0);
        assertThat(emp.get("empno").asInt(), is(100));
        assertThat(emp.get("ename").asText(), is("ename"));
    }

    @Test
    public void testCreate() throws Exception {
        when(service.create(anyObject())).then((invocation) -> {
            Emp p = invocation.getArgumentAt(0, Emp.class);
            p.setEmpno(123);
            return p;
        });

        Emp emp = new Emp(100, "ename", LocalDate.of(2017, 1, 2), 100);
        ObjectNode res = target("emps")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(emp), ObjectNode.class);

        assertThat(res.get("empno").asInt(), is(123));
        assertThat(res.get("ename").asText(), is("ename"));
    }
    
}
