
package kr.co.shopping_mall.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.shopping_mall.vo.DeliveryVO;
import kr.co.shopping_mall.vo.OrderDetailVO;
import kr.co.shopping_mall.vo.OrderInfoVO;
import kr.co.shopping_mall.vo.ProductVO;

@Repository
public class Order_DAO {
	
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	///////////// inner class ////////////
	public class SelectOrdDetailInfo implements RowMapper<OrderDetailVO>{
		public OrderDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException{
			OrderDetailVO odv=new OrderDetailVO();
			odv.setOrdd_cd(rs.getString("ordd_cd"));
			odv.setPro_cd(rs.getString("pro_cd"));
			odv.setOrdd_qty(rs.getInt("ordd_qty"));			
			return odv;
		}//mapRow
	}//SelectOrdDetailInfo	
	///////////// inner class ////////////
	public class SelectPrdInfo implements RowMapper<ProductVO>{
		public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException{
			ProductVO pv=new ProductVO();
			pv.setPro_name(rs.getString("pro_name"));
			pv.setPro_price(rs.getInt("pro_price"));
			pv.setPro_img(rs.getString("pro_img"));
			return pv;
		}//mapRow
	}//SelectPrdInfo
	/**
	 * orders, delivery, order_detail ���̺� ���� �߰�
	 * @param dVO 
	 * @throws DataAccessException
	 */
	
	@Transactional(rollbackFor= {RuntimeException.class, Exception.class})
	public void insertOrder(DeliveryVO dVO,ArrayList<ProductVO> cartList,String user_id) {
		
		ArrayList<ProductVO> cart = cartList;
		
		try {
			
			String seq="select concat('O_',lpad(ord_cd_seq.nextval,10,0)) from dual";
			String prdCode="";
			prdCode=jt.queryForObject(seq, new Object[] {}, String.class);
			
			//2. �ֹ����̺� �߰�
			String insertOrders =
					"insert into orders(ord_cd, user_id, ord_price) values(?,?,?)"; 
			int total=0;
			int totalSum=0;
			for(int i = 0; i < cart.size(); i++) {
				ProductVO pv = cart.get(i);
				total = pv.getPro_price() * pv.getCnt();
				totalSum += total;
			}
			
			//���� ����
			int cnt=jt.update(insertOrders, prdCode, user_id, totalSum);
			
			//3. ������̺� �߰� 
			String insertDelivery = "insert into delivery(ord_cd, dv_name, dv_tel, dv_addr, dv_memo) values(?,?,?,?,?)";
			int cnt2=jt.update(insertDelivery.toString(), prdCode, dVO.getDv_name(), dVO.getDv_tel(), dVO.getDv_addr(), dVO.getDv_memo());
			
			
			//4.������ ���� ����
			String insertOrderDetail = "insert into order_detail(ordd_cd,ord_cd, pro_cd, ordd_qty) values(concat('OD_',lpad(ordd_cd_seq.nextval,9,0)),?,?,?)";
			
			//cartList�� ����ִ� ������ insert ����
			int check=0;
			for(int i=0 ; i < cart.size() ;i++) {
				ProductVO pv = cart.get(i);
				check+=jt.update(insertOrderDetail.toString(), prdCode, pv.getPro_cd(), pv.getCnt());
			}
			//����� ����� list�� size�� ��
			if(check!=cart.size()) {
				System.out.println("�����߻�1");
				throw new Exception();
			}
			//1,2,3������ ���� Ƚ���� ���Ͽ� commit �� �����ϰų� rollback�� ����
			if(!(cnt==cnt2&&cnt2==check/cart.size())) {
				System.out.println("�����߻�2");
				throw new Exception();
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}//insertOrder
	
	/**
	 * �Է��� ���̵��� �ֽ� �ֹ� ��ȣ ��ȸ 
	 * @param user_id
	 * @return ord_cd
	 * @throws SQLException
	 */
	public String selectOrdCd(String user_id) throws SQLException {
		String ord_cd = "";

		String selectOrdCd = "select ord_cd	from orders	"+
		"where user_id=? and ord_date=(select max(ord_date) from orders)";
		try {
			// ��ȸ�Ǹ� ��ȸ����� ������ ����
			ord_cd = jt.queryForObject(selectOrdCd, new Object[] { user_id }, String.class);
		} catch (EmptyResultDataAccessException erdae) {
			// ��ȸ����� ���� ������ ���ܹ߻�
			ord_cd = "";
		} // end catch
		return ord_cd;
	}// selectOrdCd
	
	/**
	 * �ֹ������� ���̺� ��ȸ
	 * @param ord_cd
	 * @return returnOrdd_cd
	 * @throws SQLException
	 */
	public List<OrderDetailVO> selectOrdDetail(String ord_cd) throws SQLException {
		List<OrderDetailVO> list=null;

		String selectOrdDetail = "select ordd_cd, pro_cd, ordd_qty from order_detail where ord_cd=?";
		try {
			list = jt.query(selectOrdDetail.toString(), new Object[] { ord_cd }, new SelectOrdDetailInfo());
		} catch (EmptyResultDataAccessException erdae) {
			// ��ȸ����� ���� ������ ���ܹ߻�
			list = null;
		} // end catch
		
		return list;
	}// selectOrdDetail
	
	//�ֹ� ���ڵ�� ��ǰ���� ��ȸ
		public List<ProductVO> selectPrd(String ord_cd)throws SQLException{
			List<ProductVO> list=null;
			
			StringBuilder selectPrd=new StringBuilder();
			selectPrd.append("	select pr.pro_name,  pr.pro_img, pr.pro_price	")
			.append("	from orders od, order_stat ost, order_detail odt, product pr	")
			.append("	where ost.ord_stat_cd=od.ord_stat_cd and odt.ord_cd=od.ord_cd and pr.pro_cd=odt.pro_cd and od.ord_cd=?	");
			
			list=jt.query(selectPrd.toString(), new Object[] {ord_cd}, new SelectPrdInfo());
			
			return list;
		}//selectOrder
		
		/**
		 * ������� ���̺� ��ȸ
		 * @param ord_cd
		 * @return returnOrdd_cd
		 * @throws SQLException
		 */
		public DeliveryVO selectDelivery(String ord_cd) throws SQLException {
			DeliveryVO dVO=null;
			
			String selectDelivery = "select dv_name, dv_tel, dv_addr, dv_memo from delivery where ord_cd=?";
			try {
				// ��ȸ�Ǹ� ��ȸ����� ������ ����
				dVO = jt.queryForObject(selectDelivery, new Object[] { ord_cd }, 
						new RowMapper<DeliveryVO>() {
					public DeliveryVO mapRow(ResultSet rs, int rowNum)throws SQLException{
						DeliveryVO dVO=new DeliveryVO();
						dVO.setDv_name(rs.getString("dv_name"));
						dVO.setDv_tel(rs.getString("dv_tel"));
						dVO.setDv_addr(rs.getString("dv_addr"));
						dVO.setDv_memo(rs.getString("dv_memo"));
						return dVO;
					}
				});
			} catch (EmptyResultDataAccessException erdae) {
				// ��ȸ����� ���� ������ ���ܹ߻�
				dVO = null;
			} // end catch
			return dVO;
		}// selectId
}//class
	
