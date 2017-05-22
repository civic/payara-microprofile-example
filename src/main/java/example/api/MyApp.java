package example.api;

import example.jaxrs.MarshallingFeature;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 *
 * @author tsasaki
 */
@ApplicationPath("api")
public class MyApp extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet(super.getClasses());
        classes.add(MarshallingFeature.class);
        classes.add(EmpResource.class);
        return classes;
    }

    

	
}
