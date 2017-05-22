/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.jaxrs;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 *
 * @author tsasaki
 */
public class MarshallingFeature implements Feature{

    @Override
    public boolean configure(FeatureContext context) {
        context.register(MyJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);

        return true;

    }
    
}
