package example.dao;

import example.dto.Emp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.sql.DataSource;
import lombok.Cleanup;
import static example.util.DateTimeConverters.*;
import java.util.ArrayList;

@Dependent
public class EmpDao {
    @Inject
    private DataSource ds;
    

    public List<Emp> find() throws SQLException{
        List<Emp> emps = new ArrayList<>();
        @Cleanup Connection conn = ds.getConnection();
        @Cleanup Statement stmt = conn.createStatement();
        @Cleanup ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
        while(rs.next()){
            LocalDate hiredate = sqlDate2localDate(rs.getDate("hiredate"));
            Emp emp = new Emp(rs.getInt("id"), rs.getString("ename"), hiredate);
            emps.add(emp);
        }
        return emps;

    }

    public Emp create(Emp emp) throws SQLException {

        @Cleanup Connection conn = ds.getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement("INSERT INTO emp(ename, hiredate) values(?, ?)");

        if (emp.getEname() != null){
            stmt.setString(1, emp.getEname());
        } else {
            stmt.setNull(1, Types.VARCHAR);
        }

        if (emp.getHiredate() != null){
            stmt.setDate(2, localDate2SqlDate(emp.getHiredate()));
        } else {
            stmt.setNull(1, Types.DATE);
        }

        stmt.executeUpdate();

        //get last auto-increament empno
        @Cleanup ResultSet key = stmt.getGeneratedKeys();
        key.next();
        emp.setEmpno(key.getInt(1));
        return emp;

    }

    
}
