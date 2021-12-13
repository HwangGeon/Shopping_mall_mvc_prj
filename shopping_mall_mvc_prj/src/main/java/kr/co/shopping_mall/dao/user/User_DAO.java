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
	 * 아이디 중복 검사
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
			// 조회결과가 없을 때에는 예외발생
			returnId = "";
		} // end catch
		
		return returnId;
	}//idSearch

	
	/**
	 * 로그인
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
	 * 회원가입
	 * @param uVO
	 * @throws DataAccessException
	 */
	public void insertMember(UserVO uVO) throws DataAccessException {

		String insertMember = "insert into users(user_id, user_pw, user_name, user_tel, user_email, user_addr) values(?,?,?,?,?,?)";
		jt.update(insertMember, uVO.getUser_id(), uVO.getUser_pw(), uVO.getUser_name(), uVO.getUser_tel(),
				uVO.getUser_email(), uVO.getUser_addr());
	}// insertMember
	
	//탈퇴 플래그 변경
	public int updateDelFl(UserVO uVO) throws SQLException{
		int cnt=0;
		
		String delFlUpdate="update users set user_pw=null, user_name=null, user_tel=null, user_email=null, del_fl='y', sec_date=sysdate where user_id=?and user_pw=?";
		cnt=jt.update(delFlUpdate, uVO);
		
		return cnt;
	}//updateDelFl
	
	//마이페이지_개인정보 페이지 개인정보 조회
	public UserInfoVO selectInfo(String user_id)throws SQLException{
		UserInfoVO uv=null;
		
		String select="select u.user_id, u.user_pw, u.user_name, ud.grade_name, u.user_tel, u.user_email, u.user_addr	"
				+ "	from users u, user_grade ud	"
				+ "	where ud.grade_no=u.grade_no and u.user_id=? ";
		
		//interface를 anonymous inner class 로 생성하여 그 안에서 조회결과를 VO에 할당
		uv=jt.queryForObject(select, new Object[] { user_id }, 
				new RowMapper<UserInfoVO>() {
					//추상메소드 오버라이딩
					public UserInfoVO mapRow(ResultSet rs, int rowNum)throws SQLException{
						UserInfoVO uv=new UserInfoVO();
						//ResultSet을 사용하여 조회결과를 VO에 저장
						uv.setUser_id(rs.getString("user_id"));
						uv.setUser_name(rs.getString("user_name"));
						uv.setGrade_name(rs.getString("grade_name"));
						uv.setUser_tel(rs.getString("user_tel"));
						uv.setUser_email(rs.getString("user_email"));
						uv.setUser_addr(rs.getString("user_addr"));
						uv.setUser_pw(rs.getString("user_pw"));
						//조회결과를 저장한 uv반환
						return uv;
					}
				});	
		return uv;
	}//selectInfo
	
}//class
