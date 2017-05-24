package example.jaxrs;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * CDI Inject用のObjectMapperProducer
 * @author tsasaki
 */
@ApplicationScoped
public class ObjectMapperProducerForCDI {
    private final ObjectMapper mapper;

    public ObjectMapperProducerForCDI() {
        mapper = CustomObjectMapperResolver.getMapper();
    }

    @Produces
    public ObjectMapper inject(){
        return this.mapper;
    }
    
}
