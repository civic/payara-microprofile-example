package example.api;

import example.jaxrs.CustomObjectMapperResolver;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("api")
public class MyApp extends ResourceConfig{

    public MyApp() {
        super( JacksonFeature.class, 
                CustomObjectMapperResolver.class);

        packages("example.api");
    }
	
}
