package kr.co.shopping_mall.dao.admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.AdminVO;

@Repository
public class HomeDAO {
	
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	public String selectId(AdminVO aVO) throws DataAccessException {	
		
		String admin_id = "";
		
		String selectId = "select admin_id from admin where admin_id=? and admin_pw=?";
		admin_id = jt.queryForObject(selectId, new Object[] { aVO.getAdmin_id(), aVO.getAdmin_pw() }, String.class);
		
		return admin_id;
	}
	
	public int updatePass(AdminVO aVO) throws DataAccessException {
		int cnt = 0;

		String updatePw = "update admin set admin_pw=? where admin_id=?";
		cnt = jt.update(updatePw, aVO.getAdmin_pw(), aVO.getAdmin_id());

		return cnt;
	}
	
	public int countHomeDashPrice(String date1, String date2) throws SQLException {
		int price = 0;
		
		StringBuilder countOrder = new StringBuilder();
		countOrder.append(" select nvl(sum(ord_price),0) from orders where ord_stat_cd=? and to_char(ord_date,'YYYYMMDD') between ? and ? ");
	
		price=jt.queryForObject(countOrder.toString(), new Object[] {String.valueOf(3),String.valueOf(date1),String.valueOf(date2)},Integer.class);

		return price;
	}

	public String countHomeDashOrder(String date1, String date2) throws SQLException {
		String cnt = null;

		StringBuilder countOrder = new StringBuilder();
		countOrder.append(" select nvl(count(ord_cd),0) from orders where ord_stat_cd=? and to_char(ord_date,'YYYYMMDD') between ? and ? ");
		cnt=jt.queryForObject(countOrder.toString(), new Object[] {String.valueOf(3),String.valueOf(date1),String.valueOf(date2)},String.class);

		return cnt;
	}

}
