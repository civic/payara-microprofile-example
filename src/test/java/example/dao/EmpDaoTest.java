package example.dao;

import example.dto.Emp;
import example.dto.EmpDept;
import example.util.DateTimeConverters;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.mockito.internal.util.reflection.Whitebox;

public class EmpDaoTest {
    static SqlSessionFactory factory;
    private SqlSession session;
    private EmpDao dao;

    
    public EmpDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(stream, "test");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws UnsupportedEncodingException, SQLException, IOException {
        session = factory.openSession();

        String sql = new String(Files.readAllBytes(Paths.get("db.sql")), "utf8");
        Connection conn = session.getConnection();
        conn.createStatement().executeUpdate(sql);
        conn.commit();

        dao = new EmpDao();
        Whitebox.setInternalState(dao, "session", session);

    }
    
    @After
    public void tearDown() {
        session.close();
    }

    /**
     * Test of find method, of class EmpDao.
     * @throws java.sql.SQLException
     */
    @Test
    public void testFind() throws SQLException{
        List<Emp> result = dao.find();

        assertThat(result.size(), is(5));
        assertThat(result.get(0).toString(), 
                is("Emp(empno=1, ename=Scott, hiredate=2017-01-02, deptno=100)"));
    }

    /**
     * Test of findWithDname method, of class EmpDao.
     * @throws java.sql.SQLException
     */
    @Test
    public void testFindWithDname() throws SQLException {
        List<EmpDept> result = dao.findWithDname();

        assertThat(result.size(), is(5));
        assertThat(result.get(0).toString(), 
                is("EmpDept(empno=1, ename=Scott, hiredate=2017-01-02, deptno=100, dname=MANAGER)"));
    }

    /**
     * Test of findWithDnameByMap method, of class EmpDao.
     * @throws java.sql.SQLException
     */
    @Test
    public void testFindWithDnameByMap() throws SQLException {
        List<Map> result = dao.findWithDnameByMap();
        assertThat(result.size(), is(5));
        assertThat(result.get(0).toString(), 
                is("{ENAME=Scott, HIREDATE=2017-01-02, ID=1, DEPTNO=100, DNAME=MANAGER}"));
    }

    /**
     * Test of create method, of class EmpDao.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCreate() throws SQLException {
        Emp emp = new Emp(100, "hoge", DateTimeConverters.str2LocalDate("2017/03/21"), 200);
        Emp newEmp = dao.create(emp);

        assertThat(newEmp.getEmpno(), is(not(0)));
        List<Emp> result = dao.find();

        assertThat(result.size(), is(6));
        assertThat(result.get(5).getEmpno(), is(newEmp.getEmpno()));
    }
    
}
