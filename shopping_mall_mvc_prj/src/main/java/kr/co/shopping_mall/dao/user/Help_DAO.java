package kr.co.shopping_mall.dao.user;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Help_DAO {
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	//���̵� ã��(�̸�, �̸��Ϸ� ��ȸ)
	public String searchFindId(String inputName,String inputEmail) throws DataAccessException, EmptyResultDataAccessException{
		String user_id=""; 

		String selectId="select user_id from users where user_name=? and user_email=?";
		user_id = jt.queryForObject(selectId, new Object[] {inputName,inputEmail},  String.class);

		return user_id;
	}//findId
	
	//��й�ȣ ã��(�̸�, �̸���, ��й�ȣ�� ��ȸ)
	public String searchFindPw(String inputName,String inputEmail, String inputId) throws DataAccessException{
		String user_id=""; 

		String selectId="select user_id from users where user_name=? and user_email=? and user_id=?";
		user_id = jt.queryForObject(selectId, new Object[] {inputName,inputEmail, inputId},  String.class);

		return user_id;
	}//findId
	
	//���� ������ ��й�ȣ ����
	public int updatePass(String user_id, String user_pw) throws SQLException{
		int cnt=0;
		
		String passUpdate="update users set user_pw=? where user_id=?";
		cnt=jt.update(passUpdate, user_pw, user_id);
		
		return cnt;
	}//updatePass
}//class
