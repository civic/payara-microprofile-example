package example.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis用のSessionをInjectするProducer
 * @author tsasaki
 */
@RequestScoped
public class SQLSessionProducer {

    private static final SqlSessionFactory factory;

    static {
        InputStream stream;
        try {
            stream = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(stream);
        } catch (IOException ex) {
            Logger.getLogger(SQLSessionProducer.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Produces
    private SqlSession inject() {
        return factory.openSession();
    }

    public void closeSession(@Disposes SqlSession session){
        session.close();
    }
    
}
