package example.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import example.dto.Emp;
import example.service.DataService;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
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
    private DataService dataService;

    @Inject
    private ObjectMapper mapper;
	
	@GET
	@Path("hello")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject hello(){
		return Json.createObjectBuilder()
				.add("msg", "Hello World")
				.build();
	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectNode find() throws SQLException{
        return mapper.createObjectNode()
                .putPOJO("emps", dataService.getEmpList());

    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ObjectNode create(Emp emp) throws SQLException, JsonProcessingException{

        System.out.println(mapper.writeValueAsString(emp));

        return mapper.valueToTree(emp);
    } 

}
