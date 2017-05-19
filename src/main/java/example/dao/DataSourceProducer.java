/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;
/**
 *
 * @author tsasaki
 */
@ApplicationScoped
public class DataSourceProducer {
    private final DataSource ds;

    public DataSourceProducer() {
        JdbcDataSource h2ds = new JdbcDataSource();
        h2ds.setUrl("jdbc:h2:~/h2db/test");
        this.ds = h2ds;
    }
    @Produces
    public DataSource dataSource() throws ClassNotFoundException{
        return ds; 
    }
    
}
