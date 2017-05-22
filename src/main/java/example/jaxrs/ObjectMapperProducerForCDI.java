package example.jaxrs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Produces
    public ObjectMapper inject(){
        return this.mapper;
    }
    
}
