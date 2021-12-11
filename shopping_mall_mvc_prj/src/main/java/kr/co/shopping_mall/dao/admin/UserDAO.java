package kr.co.shopping_mall.dao.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.UserSearchVO;
import kr.co.shopping_mall.vo.UserVO;

@Repository
public class UserDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;
	
	//유저 RowMapper
			public class SelectUser implements RowMapper<UserVO> {
				public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserVO uv = new UserVO();
					uv.setNo(rs.getString("rnum"));
					uv.setUser_id(rs.getString("user_id"));
					uv.setUser_pw(rs.getString("user_pw"));
					uv.setUser_name(rs.getString("user_name"));
					uv.setUser_tel(rs.getString("user_tel"));
					uv.setUser_email(rs.getString("user_email"));
					uv.setUser_addr(rs.getString("user_addr"));
					uv.setGrade_no(rs.getString("grade_no"));
					uv.setGrade_name(rs.getString("grade_name"));
					uv.setReg_date(rs.getString("reg_date"));
					uv.setDel_fl(rs.getString("del_fl"));
					uv.setSec_date(rs.getString("sec_date"));
					return uv;
				}
			}
	
	public int selectUserDashCnt(String flag) throws DataAccessException {
		int cnt = 0;
		
		StringBuilder countUser = new StringBuilder();
		countUser.append(" select count(user_id) from users ");
			
		if(flag == "n" || flag == "y") {
			countUser.append(" where del_fl=? ");
			countUser.append(" and to_char(reg_date,'YYYYMMDD') = to_char(sysdate,'YYYYMMDD') ");
		}

		if(flag == "a") {
			countUser.append(" where del_fl='n' ");
			cnt=jt.queryForObject(countUser.toString(),Integer.class);
		} else {	
			cnt=jt.queryForObject(countUser.toString(), new Object[] {String.valueOf(flag)},Integer.class);
		}
		
		return cnt;
	}

	public List<UserVO> selectUserDashList(String flag, int start, int rowsPerPage) throws DataAccessException {
		
		List<UserVO> list = null;

		StringBuilder selectUser = new StringBuilder();
		selectUser.append(" select * ");
		selectUser.append(" from	(select rownum as rnum, u.*, g.grade_name ");
		selectUser.append("   	 from (select * from users ");
		if(!flag.equals("a")) {
			selectUser.append(" 			where del_fl=? ");
			selectUser.append(" 			and to_char(reg_date,'YYYYMMDD') = to_char(sysdate,'YYYYMMDD') ");
		} else { selectUser.append(" 				where del_fl='n' "); }
			
		selectUser.append(" 				order by reg_date desc) u, user_grade g ");
		selectUser.append(" 		 where u.grade_no = g.grade_no) ");
		selectUser.append(" where  rnum > ? and rnum <= ?+? ");  
		selectUser.append(" order by rnum ");  
		
		if(!flag.equals("a")) {
			list = jt.query(selectUser.toString(),new Object[] {String.valueOf(flag), Long.valueOf(start), Long.valueOf(rowsPerPage), Long.valueOf(start) }, new SelectUser());
		} else {
			list = jt.query(selectUser.toString(),new Object[] {Long.valueOf(start), Long.valueOf(rowsPerPage), Long.valueOf(start) }, new SelectUser());				
		}

		return list;
	}

	public int selectUserCnt(UserSearchVO usVO) throws DataAccessException {
		int cnt = 0;
		
		StringBuilder countPro = new StringBuilder();
		countPro.append(" select count(user_id) from users ");
			if(usVO.getDivision().equals("1")) {
				countPro.append(" where user_id ");
			} else {
				countPro.append(" where user_name ");	
			}
					
			countPro.append(" like '%' || ? || '%' ");
						
			if(!usVO.getUser_category().equals("a")) { 
				countPro.append(" and del_fl=? ");
						
				cnt=jt.queryForObject(countPro.toString(), new Object[] {String.valueOf(usVO.getSearchValue()),String.valueOf(usVO.getUser_category())},Integer.class);
			} else {	
				cnt=jt.queryForObject(countPro.toString(), new Object[] {String.valueOf(usVO.getSearchValue())},Integer.class);
			}
		
		return cnt;
	}

	public List<UserVO> selectUserList(UserSearchVO usVO, int start, int rowsPerPage) {
		List<UserVO> list = null;

		StringBuilder selectUser = new StringBuilder();
		selectUser.append(" select * ") 
				 .append(" from (select rownum as rnum, u.*, g.grade_name ")
		         		.append(" from ( select * ")
		         				.append(" from users ");
					if(usVO.getDivision().equals("1")) {
						selectUser.append("where user_id");		 
					} else {
						selectUser.append("where user_name");
					}
						selectUser.append(" like '%' || ? || '%' ");
					if(!usVO.getUser_category().equals("a")) {
						selectUser.append(" and del_fl=? ");
					}
						selectUser.append(" order by reg_date desc) u, user_grade g ")
								 .append(" where u.grade_no = g.grade_no) ")
							 	 .append(" where rnum > ? and rnum <= ?+? ")	
							 	 .append(" order by rnum ");
		if (usVO.getUser_category().equals("a")) {
			// 코드가 a면 전체분류의 유저 조회
			list = jt.query(selectUser.toString(),new Object[] {String.valueOf(usVO.getSearchValue()), Long.valueOf(start), Long.valueOf(rowsPerPage), Long.valueOf(start) }, new SelectUser());
		} else {
			// 카테고리별 유저정보를 조회
			list = jt.query(selectUser.toString(), new Object[] {String.valueOf(usVO.getSearchValue()), String.valueOf(usVO.getUser_category()),Long.valueOf(start), Long.valueOf(rowsPerPage), Long.valueOf(start) },new SelectUser());
		} // end else

		return list;
	}

	public UserVO selectUserInfo(String user_id) throws DataAccessException {
		UserVO uVO=null;
		
		String selectUserInfo=" select rownum as rnum, u.*, g.grade_name from users u, user_grade g where u.grade_no = g.grade_no and user_id=? ";
		uVO=jt.queryForObject(selectUserInfo, new Object[] { String.valueOf(user_id) }, new SelectUser());

		return uVO;
	}

	public int updateUser(UserVO uVO) throws DataAccessException {
		int cnt = 0;

		String updateUser = " update users set user_name=?, grade_no=?, user_tel=?, user_addr=?, user_email=? where user_id=?";
		cnt = jt.update(updateUser, uVO.getUser_name(), uVO.getGrade_no(), uVO.getUser_tel(), uVO.getUser_addr(), uVO.getUser_email(), uVO.getUser_id());
		
		return cnt;
	}

	public int secessionUser(String user_id) throws DataAccessException {
		int cnt = 0;

		String secessionUser = " update users set user_pw=null, user_name=null, user_tel=null, user_email=null, del_fl='y', sec_date=sysdate where user_id=?";
		cnt = jt.update(secessionUser, user_id);
	
		return cnt;
	}

}
