package kr.co.shopping_mall.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.UserInfoVO;
import kr.co.shopping_mall.vo.UserVO;

@Repository
public class User_DAO {
	
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	/**
	 * ���̵� �ߺ� �˻�
	 * @param user_id
	 * @return returnId
	 * @throws SQLException
	 */
	public String idSearch(String user_id) throws SQLException {	
		
		String returnId = "";
		
		String idSearch = "select user_id from users where user_id=? ";
		try {
		returnId = jt.queryForObject(idSearch, new Object[] { user_id }, String.class);
		} catch (Exception erdae) {
			// ��ȸ����� ���� ������ ���ܹ߻�
			returnId = "";
		} // end catch
		
		return returnId;
	}//idSearch

	
	/**
	 * �α���
	 * @param uVO
	 * @return user_id
	 * @throws DataAccessException
	 */
	public String selectId(UserVO uVO) throws DataAccessException {	
		
		String user_id = "";
		
		String selectId = "select user_id from users where user_id=? and user_pw=?";
		user_id = jt.queryForObject(selectId, new Object[] { uVO.getUser_id(),uVO.getUser_pw() }, String.class);
		
		return user_id;
	}//selectId
	
	
	/**
	 * ȸ������
	 * @param uVO
	 * @throws DataAccessException
	 */
	public void insertMember(UserVO uVO) throws DataAccessException {

		String insertMember = "insert into users(user_id, user_pw, user_name, user_tel, user_email, user_addr) values(?,?,?,?,?,?)";
		jt.update(insertMember, uVO.getUser_id(), uVO.getUser_pw(), uVO.getUser_name(), uVO.getUser_tel(),
				uVO.getUser_email(), uVO.getUser_addr());
	}// insertMember
	
	//Ż�� �÷��� ����
	public int updateDelFl(UserVO uVO) throws SQLException{
		int cnt=0;
		
		String delFlUpdate="update users set user_pw=null, user_name=null, user_tel=null, user_email=null, del_fl='y', sec_date=sysdate where user_id=?and user_pw=?";
		cnt=jt.update(delFlUpdate, uVO);
		
		return cnt;
	}//updateDelFl
	
	//����������_�������� ������ �������� ��ȸ
	public UserInfoVO selectInfo(String user_id)throws SQLException{
		UserInfoVO uv=null;
		
		String select="select u.user_id, u.user_pw, u.user_name, ud.grade_name, u.user_tel, u.user_email, u.user_addr	"
				+ "	from users u, user_grade ud	"
				+ "	where ud.grade_no=u.grade_no and u.user_id=? ";
		
		//interface�� anonymous inner class �� �����Ͽ� �� �ȿ��� ��ȸ����� VO�� �Ҵ�
		uv=jt.queryForObject(select, new Object[] { user_id }, 
				new RowMapper<UserInfoVO>() {
					//�߻�޼ҵ� �������̵�
					public UserInfoVO mapRow(ResultSet rs, int rowNum)throws SQLException{
						UserInfoVO uv=new UserInfoVO();
						//ResultSet�� ����Ͽ� ��ȸ����� VO�� ����
						uv.setUser_id(rs.getString("user_id"));
						uv.setUser_name(rs.getString("user_name"));
						uv.setGrade_name(rs.getString("grade_name"));
						uv.setUser_tel(rs.getString("user_tel"));
						uv.setUser_email(rs.getString("user_email"));
						uv.setUser_addr(rs.getString("user_addr"));
						uv.setUser_pw(rs.getString("user_pw"));
						//��ȸ����� ������ uv��ȯ
						return uv;
					}
				});	
		return uv;
	}//selectInfo
	
}//class
