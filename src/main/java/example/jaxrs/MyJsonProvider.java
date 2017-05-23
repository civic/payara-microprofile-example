/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.jaxrs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

/**
 *
 * @author tsasaki
 */
public class MyJsonProvider extends JacksonJaxbJsonProvider{
    private ObjectMapper mapper;

    public MyJsonProvider() {
        super();

        System.out.println("MyJsonProvider");
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        setMapper(mapper);

    }

    
    
    
}
