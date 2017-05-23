package example.api;

import example.jaxrs.MyJsonProvider;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;


/**
 *
 * @author tsasaki
 */
@ApplicationPath("api")
public class MyApp extends ResourceConfig{

    public MyApp() {
        register(MyJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
        register(JacksonFeature.class);
        packages("example.api");
    }
	
}
