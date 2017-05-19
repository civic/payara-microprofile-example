package example.api;

import example.service.DataService;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
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
    public JsonObject find() throws SQLException{
        JsonArrayBuilder emps = Json.createArrayBuilder();


        dataService.getEmpList().stream().forEach(emp -> { 
            emps.add(emp.toJsonObject()); 
        });

        return Json.createObjectBuilder()
                .add("emps", emps.build())
                .build();

    }

}
