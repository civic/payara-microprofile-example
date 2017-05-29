package example.service;

import example.dao.EmpDao;
import example.dto.Emp;
import example.dto.EmpDept;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * DataStoreSerivce
 */
@Dependent
public class EmpService {
    @Inject
    private EmpDao empDao;

    public List<Emp> getEmpList() throws SQLException {
        List<Emp> emps = empDao.find();
        return emps;
    }
    public List<EmpDept> getEmpDeptList() throws SQLException {
        List<EmpDept> emps = empDao.findWithDname();
        return emps;
    }

    public Emp create(Emp emp) throws SQLException {

        return empDao.create(emp);
    }

}
