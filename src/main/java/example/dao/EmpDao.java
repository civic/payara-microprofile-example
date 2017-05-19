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
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.sql.DataSource;

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
        try (Connection conn = ds.getConnection()){
            try (Statement stmt = conn.createStatement()){
                try(ResultSet rs = stmt.executeQuery("SELECT * FROM emp")){
                    while(rs.next()){

                        Emp emp = new Emp(rs.getInt("id"), rs.getString("ename"), null);
                        emps.add(emp);

                    }
                }
            }
        }
        return emps;

    }

    
}
