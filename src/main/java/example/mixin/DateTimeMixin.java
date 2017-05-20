package example.mixin;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author tsasaki
 */
public interface DateTimeMixin {
    default String dateFormat(Date d){
        return LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
    
}
