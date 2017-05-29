package example.dao;

import example.dto.Emp;
import example.dto.EmpDept;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.session.SqlSession;

@Dependent
public class EmpDao {
    @Inject
    private SqlSession session;
    
    public List<Emp> find() throws SQLException{
        return session.getMapper(EmpMapper.class).select();
    }

    /**
     * EmpDeptのリストを返すJOIN検索の実装。
     * JOINした結果を保持するクラスを作成し、その検索結果を返す。
     * 型安全になるが、JOIN結果行に対するクラス(EmpDept)を作成する必要がある
     * @return
     * @throws SQLException 
     */
    public List<EmpDept> findWithDname() throws SQLException{
        return session.getMapper(EmpMapper.class).selectWithDept();
    }
    /**
     * Mapのリストを返すJOIN検索の実装。
     * Mapに検索結果の1行を返す。 JOIN結果行に対するクラスを作成せず、
     * 汎用的に使えるMapに1行を格納。列名でデータを指定するので型安全にならない。
     * @return
     * @throws SQLException 
     */
    public List<Map> findWithDnameByMap() throws SQLException{
        return session.getMapper(EmpMapper.class).selectWithDeptByMap();
    }

    public Emp create(Emp emp) throws SQLException {
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        mapper.create(emp);
        session.commit();
        Emp newEmp = mapper.get(emp.getEmpno());
        return newEmp;
    }

    public static interface EmpMapper {
        
        @Results(id="empResult", value = {
            @Result(property = "empno", column = "id", id=true),
        })
        @Select("SELECT * FROM emp")
        List<Emp> select();

        @SelectKey(statement = "CALL IDENTITY()", keyProperty = "empno", before=false, resultType = int.class)
        @Insert("INSERT INTO emp(ename, hiredate) VALUES(#{ename}, #{hiredate})")
        int create(Emp emp);

        @ResultMap("empResult") //reference to "method select @Results"
        @Select("SELECT * FROM emp WHERE id = #{empno}")
        Emp get(int empno);

        /**
         * EmpDept DTO（EmpとDeptをJOINした結果の行オブジェクト）を津kった検索
         * @return 
         */
        @Results({
            @Result(property = "empno", column = "id", id=true),
        })
        @Select("SELECT e.*, d.dname FROM EMP e LEFT JOIN dept d ON e.deptno = d.id")
        List<EmpDept> selectWithDept();


        @Select("SELECT e.*, d.dname FROM EMP e LEFT JOIN dept d ON e.deptno = d.id")
        List<Map> selectWithDeptByMap();
    }

    
}
