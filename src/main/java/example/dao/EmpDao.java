/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.dao;

import example.dto.Emp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.sql.DataSource;
import lombok.Cleanup;

/**
 *
 * @author tsasaki
 */
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
            Emp emp = new Emp(rs.getInt("id"), rs.getString("ename"), new Date(rs.getDate("hiredate").getTime()));
            emps.add(emp);
        }
        return emps;

    }

    
}
