package kr.co.shopping_mall.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.UserVO;

@Repository
public class User_DAO {
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	public String selectId(UserVO uVO) throws DataAccessException {	
		
		String user_id = "";
		String selectId = "select user_id from user where user_id=? and user_pw=?";
		user_id = jt.queryForObject(selectId, new Object[] { uVO.getUser_id(), uVO.getUser_pw() }, String.class);
		
		return user_id;
	}
}
