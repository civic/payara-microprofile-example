/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.jaxrs;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author tsasaki
 */
public class DateDeserializer extends JsonDeserializer<Date>{


    @Override
    public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {

        LocalDateTime ldt = LocalDate.parse(jp.getText(), DateTimeFormatter.ofPattern("yyyy/MM/dd")).atStartOfDay();

        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
    
}
