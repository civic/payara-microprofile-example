/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.api;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author tsasaki
 */
@Path("myapi")
public class MyApi {
	
	@GET
	@Path("hello")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject hello(){
		return Json.createObjectBuilder()
				.add("msg", "Hello World")
				.build();
	}
}
