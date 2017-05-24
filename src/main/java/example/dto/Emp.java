package example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import example.jaxrs.DateDeserializer;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Emp Data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private int empno;
    private String ename;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "JST")
    @JsonDeserialize(using = DateDeserializer.class)
    private Date hiredate;

}
