package kr.co.sist.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kr.co.sist.vo.EmpVO;

@Component
public class EmpDAO {
	
	@Autowired(required = false)
	private JdbcTemplate jt; //의존성 주입.

	public List<EmpVO> selectAllEmp()throws SQLException{
		List<EmpVO> list=null;
		
		StringBuilder selectEmp=new StringBuilder();
		selectEmp
		.append("	select		empno,ename,deptno,job,to_char(hiredate,'yyyy-mm-dd') hiredate,	sal	")
		.append("	from	cp_emp2")
		.append("	order	 by hiredate desc");
		
		list=jt.query(selectEmp.toString(), new RowMapper<EmpVO>() {
			@Override
			public EmpVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				EmpVO eVO=new EmpVO();
				
				eVO.setEmpno(rs.getInt("empno"));
				eVO.setSal(rs.getInt("sal"));
				eVO.setDeptno(rs.getInt("deptno"));
				eVO.setEname(rs.getString("ename"));
				eVO.setJob(rs.getString("job"));
				eVO.setHiredate(rs.getString("hiredate"));
				
				return eVO;
			}
		});
		
		return list;
	}//selectAllEmp
	
}
