package kr.co.shopping_mall.dao.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;
	
	public int countUserDashData(String flag) {
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

}
