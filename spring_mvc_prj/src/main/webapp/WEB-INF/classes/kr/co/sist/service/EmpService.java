package kr.co.sist.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.sist.dao.EmpDAO;
import kr.co.sist.vo.EmpVO;

@Component
public class EmpService {
	@Autowired
	private EmpDAO eDAO;
	
	public List<EmpVO> searchAllEmp()throws SQLException{
		List<EmpVO> list=null;
		
		list=eDAO.selectAllEmp();
		
		return list;
	}//searchAllEmp
}//class
