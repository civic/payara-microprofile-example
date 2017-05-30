package example.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import example.dto.Emp;
import example.service.EmpService;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * JAX-RSのリソースクラスEmp
 */
@Path("emps")
@RequestScoped
public class EmpResource{

    @Inject
    private EmpService empService;

    @Inject
    private ObjectMapper mapper;
	
    /**
     * Empの一覧をJSONで返す。
     * @return
     * @throws SQLException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectNode find() throws SQLException{
        return mapper.createObjectNode()
                .putPOJO("emps", empService.getEmpList());
    }

    /**
     * EmpとDeptをJOINした結果をJSONで返す。
     * EmpとDeptをJOINしたオブジェクトをクラスとして実装
     * @return
     * @throws SQLException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("with-dname")
    public ObjectNode findWithDname() throws SQLException{
        return mapper.createObjectNode()
                .putPOJO("emps", empService.getEmpDeptList());
    }

    /**
     * EmpとDeptをJOINした結果をJSONで返すその2。
     * findWithDnameと同じ結果JSONを返すが、EmpDeptを作成せずにMapから生成する
     * @return
     * @throws SQLException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("with-dname-by-map")
    public ObjectNode findWithDnameByMap() throws SQLException{
        return mapper.createObjectNode()
                .putPOJO("emps", empService.getEmpDeptListByMap());
    }

    /**
     * Empを1件作成する
     * @param emp
     * @return
     * @throws SQLException
     * @throws JsonProcessingException 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ObjectNode create(Emp emp) throws SQLException, JsonProcessingException{

        System.out.println(mapper.writeValueAsString(emp));

        emp = empService.create(emp);

        return mapper.valueToTree(emp);
    } 

}
