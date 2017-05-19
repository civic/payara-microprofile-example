/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.dto;

import example.mixin.DateTimeMixin;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 *
 * @author tsasaki
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp implements DateTimeMixin {
    private int empno;
    private String ename;
    private Date hiredate;

    public JsonObject toJsonObject(){
        
        JsonObjectBuilder builder =Json.createObjectBuilder()
                    .add("id", empno)
                    .add("ename", ename);

        if (hiredate == null){
            builder.addNull("hiredata");
        } else {
            builder.add("hiredate", dateFormat(hiredate));
        }
        return builder.build();

    }
}
