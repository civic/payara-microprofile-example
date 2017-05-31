package example.service;

import example.dao.EmpDao;
import example.dto.Emp;
import example.dto.EmpDept;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    public List<Map<String, Object>> getEmpDeptListByMap() throws SQLException {
        List<Map<String, Object>> emps = new ArrayList<>();

        //SQL検索結果のMapでは列名(大文字)のままになっているので
        //それを修正しつつ、ID->empnoの属性もマッピングする
        //上記のEmpDeptで取得するパターンでは、検索結果をオブジェクトにマッピングする定義でそれを行っている
        empDao.findWithDnameByMap().stream().forEach((row) -> {
            Map<String, Object> newRow = new LinkedHashMap<>();
            newRow.put("empno", row.get("ID"));
            newRow.put("ename", row.get("ENAME"));
            newRow.put("hiredate", row.get("HIREDATE"));
            newRow.put("deptno",row.get("DEPTNO"));
            newRow.put("dname",row.get("DNAME"));
            emps.add(newRow);
        });
        return emps;

    }

    public Emp create(Emp emp) throws SQLException {

        return empDao.create(emp);
    }

}
