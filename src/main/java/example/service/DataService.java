package example.service;

import example.dao.EmpDao;
import example.dto.Emp;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * DataStoreSerivce
 */
@Dependent
public class DataService {
    @Inject
    private EmpDao empDao;
    
    public List<Emp> getEmpList() throws SQLException {
        List<Emp> emps = empDao.find();
        return emps;
    }
}
