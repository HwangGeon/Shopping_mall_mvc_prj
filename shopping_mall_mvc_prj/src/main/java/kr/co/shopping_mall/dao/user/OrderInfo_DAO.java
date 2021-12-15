package kr.co.shopping_mall.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.OrderInfoVO;

@Repository
public class OrderInfo_DAO {
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	///////////// inner class ////////////
	public class SelectOrderInfo implements RowMapper<OrderInfoVO>{
		public OrderInfoVO mapRow(ResultSet rs, int rowNum) throws SQLException{
			OrderInfoVO oiv=new OrderInfoVO();
			oiv.setPro_name(rs.getString("pro_name"));
			oiv.setOrd_cd(rs.getString("ord_cd"));
			oiv.setOrd_date(rs.getString("ord_date"));
			oiv.setOrd_stat_name(rs.getString("ord_stat_name"));
			oiv.setOrdd_cd(rs.getString("ordd_cd"));
			oiv.setOrd_price(rs.getInt("ord_price"));
			oiv.setOrdd_qty(rs.getInt("ordd_qty"));
			return oiv;
		}//mapRow
	}//SelectOrderInfo	
	
	//주문정보 조회
	public List<OrderInfoVO> selectOrder(String user_id)throws SQLException{
		List<OrderInfoVO> list=null;
		
		StringBuilder selectOrder=new StringBuilder();
		selectOrder.append("	select pr.pro_name, od.ord_cd, od.ord_date, od.ord_price, ost.ord_stat_name, odt.ordd_cd, odt.ordd_qty		")
		.append("	from orders od, order_stat ost, order_detail odt, product pr	")
		.append("	where ost.ord_stat_cd=od.ord_stat_cd and odt.ord_cd=od.ord_cd and pr.pro_cd=odt.pro_cd and od.user_id=?	")
		.append("	order by od.ord_date desc 	");
		
		list=jt.query(selectOrder.toString(), new Object[] {user_id}, new SelectOrderInfo());
		
		return list;
	}//selectOrder
	
	//주문상태 주문취소로 변경
	public int updateOrderInfo(String ord_cd) throws DataAccessException{
		int cnt=0;

		String updateOrder="update orders set ord_stat_cd='4' where ord_cd=?";
		
		//update orders set ord_stat_cd='4' where ord_cd=?;
		cnt=jt.update(updateOrder,ord_cd);
		
		return cnt;
	}//updateOrderInfo	
	
}//class
