package example.service;

import example.dao.EmpDao;
import example.dto.Emp;
import example.dto.EmpDept;
import example.util.DateTimeConverters;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * EmpServiceのテスト。
 * EmpDaoをMockに置き換えてServiceが正しくDaoを使ってデータを返すかテストする。
 */
public class EmpServiceTest {
    @Mock
    private EmpDao empDao;

    @InjectMocks
    private EmpService service;
    
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test of getEmpList method, of class EmpService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetEmpList() throws Exception {
        when(empDao.find()).thenReturn(Arrays.asList(
                new Emp(1, "bar", DateTimeConverters.str2LocalDate("2017/03/11"), 100)
                ));

        List<Emp> emps = service.getEmpList();
        assertThat(emps.size(), is(1));
    }

    /**
     * Test of getEmpDeptList method, of class EmpService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetEmpDeptList() throws Exception {
        when(empDao.findWithDname()).thenReturn(Arrays.asList(
                new EmpDept(1, "bar", DateTimeConverters.str2LocalDate("2017/03/11"), 100, "somejob")
                ));

        List<EmpDept> emps = service.getEmpDeptList();
        assertThat(emps.size(), is(1));
    }

    /**
     * Test of getEmpDeptListByMap method, of class EmpService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetEmpDeptListByMap() throws Exception {

        List<Map> list = IntStream.range(0, 3).mapToObj(i -> {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("ID", i);
            row.put("ENAME", "name-"+i);
            row.put("HIREDATE", DateTimeConverters.str2LocalDate("2017/03/1"+i));
            row.put("DEPTNO", i+100);
            row.put("DNAME", "dept-"+i);
            return row;
        }).collect(Collectors.toList());

        when(empDao.findWithDnameByMap()).thenReturn(list);

        List<Map<String,Object>> emps = service.getEmpDeptListByMap();
        assertThat(emps.size(), is(3));
        Map<String, Object> row = emps.get(0);
        assertThat(row.get("empno"), is(0));
        assertThat(row.get("ename"), is("name-0"));
        assertThat(row.get("hiredate"), is(DateTimeConverters.str2LocalDate("2017/03/10")));
        assertThat(row.get("deptno"), is(100));
        assertThat(row.get("dname"), is("dept-0"));
    }

    /**
     * Test of create method, of class EmpService.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
        Emp srcEmp = new Emp(0, "bar", DateTimeConverters.str2LocalDate("2017/03/11"), 100);
        when(empDao.create(srcEmp)).then(inv -> {
            return new Emp(123, srcEmp.getEname(), srcEmp.getHiredate(), srcEmp.getDeptno());
        });

        Emp newEmp = service.create(srcEmp);
        assertThat(newEmp.getEmpno(), is(123));
    }
    
}
