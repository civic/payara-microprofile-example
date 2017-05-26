package example.util;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日付時刻関係の変換クラス
 * @author tsasaki
 */
public class DateTimeConverters {
    public static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static final DateTimeFormatter DEFAULT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static LocalDate str2LocalDate(String dateString){
        return str2LocalDate(dateString, DEFAULT_DATE_FORMAT);
    }
    public static LocalDate str2LocalDate(String dateString, DateTimeFormatter pattern){
        return LocalDate.parse(dateString, pattern);
    }
    public static String localDate2str(LocalDate ld){
        return localDate2str(ld, DEFAULT_DATE_FORMAT);
    }
    public static String localDate2str(LocalDate ld, DateTimeFormatter pattern){
        return ld.format(pattern);
    }
    public static Date localDate2SqlDate(LocalDate ld){
        ZonedDateTime zdt = ld.atStartOfDay().atZone(ZoneId.systemDefault()); 
        return new java.sql.Date(zdt.toInstant().toEpochMilli());
    }

    public static LocalDate sqlDate2localDate(java.sql.Date sqlDate){
        ZonedDateTime zdt = Instant.ofEpochMilli(sqlDate.getTime()).atZone(ZoneId.systemDefault()); 
        return LocalDate.from(zdt);
    }

}
