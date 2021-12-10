package kr.co.shopping_mall.dao.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.OrderDetailVO;
import kr.co.shopping_mall.vo.OrderSearchVO;
import kr.co.shopping_mall.vo.OrderVO;

@Repository
public class OrderDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;

	public int selectOrderDashCnt(String flag) throws DataAccessException {
		
		int cnt = 0;
		
		StringBuilder countOrder = new StringBuilder();
		countOrder.append(" select count(ord_cd) from orders where ord_stat_cd=? ");
	
		cnt=jt.queryForObject(countOrder.toString(), new Object[] {String.valueOf(flag)},Integer.class);
		
		return cnt;
	}

	public List<OrderVO> selectOrderDashList(String flag, int start, int rowsPerPage) {
		
		List<OrderVO> list = null;

		StringBuilder selectOrder = new StringBuilder();
		
		selectOrder.append(" select * ");
		selectOrder.append(" from	(select rownum as rnum, o.*, s.ord_stat_name ");
		selectOrder.append("   	 	 from (select * from orders ");
		selectOrder.append(" 				where ord_stat_cd=? ");
		selectOrder.append(" 				order by ord_date desc) o, order_stat s ");  
		selectOrder.append(" 		 where o.ord_stat_cd = s.ord_stat_cd) ");  
		selectOrder.append(" where  rnum > ? and rnum <= ?+? ");  
		selectOrder.append(" order by rnum ");  
		
		list = jt.query(selectOrder.toString(),new Object[] {String.valueOf(flag), Long.valueOf(start), Long.valueOf(rowsPerPage), Long.valueOf(start) }, 
				new RowMapper<OrderVO>() {
					public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						OrderVO oVO = new OrderVO();
						oVO.setNo(rs.getString("rnum"));
						oVO.setOrd_cd(rs.getString("ord_cd"));
						oVO.setOrd_date(rs.getString("ord_date"));
						oVO.setOrd_stat_name(rs.getString("ord_stat_name"));
						return oVO;
					}
				});

		return list;
	}

	public int selectOrderCnt(OrderSearchVO osVO) throws DataAccessException {
		int cnt = 0;
		
		StringBuilder countOrder = new StringBuilder();
		countOrder.append(" select count(ord_cd) from orders ");
			
		if(osVO.getDivision().equals("1")) {
			countOrder.append(" where ord_cd ");
		} else {
			countOrder.append(" where user_id ");	
		}
		countOrder.append(" like '%' || ? || '%' ");
		countOrder.append(" and to_char(ord_date,'YYYYMMDD') between ? and ? ");

		if(!osVO.getOrder_stat_cd().equals("0")) {
			countOrder.append(" and ord_stat_cd=? ");
			cnt=jt.queryForObject(countOrder.toString(), new Object[] {String.valueOf(osVO.getSearchValue()),String.valueOf(osVO.getOrder_date1()),String.valueOf(osVO.getOrder_date2()),Long.valueOf(osVO.getOrder_stat_cd())},Integer.class);
		} else {	
			cnt=jt.queryForObject(countOrder.toString(), new Object[] {String.valueOf(osVO.getSearchValue()),String.valueOf(osVO.getOrder_date1()),String.valueOf(osVO.getOrder_date2())},Integer.class);
		}
		
		return cnt;
	}

	public List<OrderVO> selectOrderList(OrderSearchVO osVO, int start, int rowsPerPage) throws DataAccessException {
		
		List<OrderVO> list = null;

		StringBuilder searchOrder = new StringBuilder();
		searchOrder.append(" select * ");
		searchOrder.append(" from(select rownum as rnum, os.* ");
		searchOrder.append(" 		from (select o.ord_cd, o.ord_date, s.ord_stat_name ");
		searchOrder.append(" 				from orders o, order_stat s ");
		searchOrder.append(" 				where o.ord_stat_cd = s.ord_stat_cd and to_char(o.ord_date,'YYYYMMDD') between ? and ?");
		if(!osVO.getOrder_stat_cd().equals("0")) {
			searchOrder.append(" 				and o.ord_stat_cd = ? ");
		}
		if(osVO.getDivision().equals("1")) {
		searchOrder.append(" 				and o.ord_cd ");
		} else {
		searchOrder.append(" 				and o.user_id ");
		}
		searchOrder.append(" 				like '%' || ? || '%' ");
		searchOrder.append(" 				order by ord_date desc) os) ");
		searchOrder.append(" 		where  rnum > ? and rnum <= ?+? ");
		searchOrder.append(" 		order by rnum ");
		
		if(!osVO.getOrder_stat_cd().equals("0")) {
		list = jt.query(searchOrder.toString(), new Object[] { String.valueOf(osVO.getOrder_date1()),String.valueOf(osVO.getOrder_date2()),Long.valueOf(osVO.getOrder_stat_cd()),String.valueOf(osVO.getSearchValue()),Long.valueOf(start),Long.valueOf(rowsPerPage),Long.valueOf(start) }, 
				new RowMapper<OrderVO>() {
					public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						OrderVO oVO = new OrderVO();
						oVO.setNo(rs.getString("rnum"));
						oVO.setOrd_cd(rs.getString("ord_cd"));
						oVO.setOrd_date(rs.getString("ord_date"));
						oVO.setOrd_stat_name(rs.getString("ord_stat_name"));
						return oVO;
					}
				});	
		} else {
			list = jt.query(searchOrder.toString(), new Object[] { String.valueOf(osVO.getOrder_date1()),String.valueOf(osVO.getOrder_date2()),String.valueOf(osVO.getSearchValue()),Long.valueOf(start),Long.valueOf(rowsPerPage),Long.valueOf(start) }, 
					new RowMapper<OrderVO>() {
						public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
							OrderVO oVO = new OrderVO();
							oVO.setNo(rs.getString("rnum"));
							oVO.setOrd_cd(rs.getString("ord_cd"));
							oVO.setOrd_date(rs.getString("ord_date"));
							oVO.setOrd_stat_name(rs.getString("ord_stat_name"));
							return oVO;
						}
					});	
		}
		
		return list;
	}

	public OrderVO selectOrderInfo(String ord_cd) throws DataAccessException {
		OrderVO oVO = null;
		
		StringBuilder selectOrderInfo = new StringBuilder();
		selectOrderInfo.append(" select o.ord_cd, o.ord_date, o.ord_stat_cd, o.ord_price, d.dv_name, d.dv_tel, d.dv_addr, d.dv_memo ");
		selectOrderInfo.append(" from orders o, delivery d");
		selectOrderInfo.append(" where o.ord_cd = d.ord_cd and o.ord_cd = ? ");
		oVO=jt.queryForObject(selectOrderInfo.toString(), new Object[] { String.valueOf(ord_cd) }, 
				new RowMapper<OrderVO>() {
					public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						OrderVO ov = new OrderVO();
						ov.setOrd_cd(rs.getString("ord_cd"));
						ov.setOrd_date(rs.getString("ord_date"));
						ov.setDv_name(rs.getString("dv_name"));
						ov.setDv_tel(rs.getString("dv_tel"));
						ov.setDv_addr(rs.getString("dv_addr"));
						ov.setDv_memo(rs.getString("dv_memo"));
						ov.setOrd_stat_cd(rs.getInt("ord_stat_cd"));
						ov.setOrd_price(rs.getInt("ord_price"));
						return ov;
					}
				});	
		
		return oVO;
	}

	public List<OrderDetailVO> getOrderDetailInfo(String ord_cd) throws DataAccessException {
		List<OrderDetailVO> orderDetailList = null;

		StringBuilder selectOrderInfo = new StringBuilder();
		selectOrderInfo.append(" select p.pro_name, d.ordd_qty, p.pro_price ");
		selectOrderInfo.append(" from order_detail d , product p ");
		selectOrderInfo.append(" where d.pro_cd = p.pro_cd and d.ord_cd=? ");
		orderDetailList = jt.query(selectOrderInfo.toString(), new Object[] { String.valueOf(ord_cd) }, 
				new RowMapper<OrderDetailVO>() {
					public OrderDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						OrderDetailVO odVO = new OrderDetailVO();
						odVO.setPro_name(rs.getString("pro_name"));
						odVO.setOrdd_qty(rs.getInt("ordd_qty"));
						odVO.setPro_price(rs.getInt("pro_price"));
						return odVO;
					}
				});	
		
		return orderDetailList;
	}

	public int updateOrder(OrderVO oVO) {

		int cnt = 0;
		
		String updateOrder = " update orders set ord_stat_cd=? where ord_cd=?";
		cnt = jt.update(updateOrder, oVO.getOrd_stat_cd(), oVO.getOrd_cd());
					
		updateOrder = " update delivery set dv_addr=? where ord_cd=?";
		cnt += jt.update(updateOrder, oVO.getDv_addr(), oVO.getOrd_cd());
		
		return cnt;
	}
}
