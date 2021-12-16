package kr.co.shopping_mall.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.UserInfoVO;

@Repository
public class UserInfo_DAO {
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	//개인정보 조회
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
	
	//비밀번호 변경
	public int updatePass(String user_id, String user_pw) throws SQLException{
		int cnt=0;
		
		String passUpdate="update users set user_pw=? where user_id=?";
		cnt=jt.update(passUpdate, user_pw, user_id);
		
		return cnt;
	}//updatePass
	
	//개인정보수정
	public int updateInfo(String user_id, String email, String addr) throws SQLException{
		int cnt=0;
		
		String infoUpdate="update users set user_email=?, user_addr=? where user_id=?";
		cnt=jt.update(infoUpdate, email, addr, user_id);

		return cnt;
	}//updateInfo
	
}//class
