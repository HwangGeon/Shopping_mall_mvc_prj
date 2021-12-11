package kr.co.shopping_mall.dao.user;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.UserVO;

@Repository
public class User_DAO {
	
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	public String idSearch(String user_id) throws SQLException {	
		
		String returnId = "";
		
		String idSearch = "select user_id from users where user_id=? ";
		try {
		returnId = jt.queryForObject(idSearch, new Object[] { user_id }, String.class);
		} catch (Exception erdae) {
			// 조회결과가 없을 때에는 예외발생
			returnId = "";
		} // end catch
		
		return returnId;
	}//selectId
	
	public String selectId(UserVO uVO) throws DataAccessException {	
		
		String user_id = "";
		
		String selectId = "select user_id from users where user_id=? and user_pw=?";
		user_id = jt.queryForObject(selectId, new Object[] { uVO.getUser_id(),uVO.getUser_pw() }, String.class);
		
		return user_id;
	}//selectId
	
	public void insertMember(UserVO uVO) throws DataAccessException {

		String insertMember = "insert into users(user_id, user_pw, user_name, user_tel, user_email, user_addr) values(?,?,?,?,?,?)";
		jt.update(insertMember, uVO.getUser_id(), uVO.getUser_pw(), uVO.getUser_name(), uVO.getUser_tel(),
				uVO.getUser_email(), uVO.getUser_addr());
	}// insertMember
	
}//class
