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
 *
 * @author tsasaki
 */
@Path("emps")
@RequestScoped
public class EmpResource{

    @Inject
    private EmpService empService;

    @Inject
    private ObjectMapper mapper;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectNode find() throws SQLException{
        return mapper.createObjectNode()
                .putPOJO("emps", empService.getEmpList());

    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ObjectNode create(Emp emp) throws SQLException, JsonProcessingException{

        System.out.println(mapper.writeValueAsString(emp));

        return mapper.valueToTree(emp);
    } 

}
