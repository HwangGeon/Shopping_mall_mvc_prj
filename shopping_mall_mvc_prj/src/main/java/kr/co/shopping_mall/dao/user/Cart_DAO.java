package kr.co.shopping_mall.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.ProductVO;

@Repository
public class Cart_DAO {
	@Autowired(required = false)
	private JdbcTemplate jt;
	
	///////////// inner class : 카테고리별 상품정보를 저장할 목적의 클래스 시작////////////
	public class SelectPro implements RowMapper<ProductVO>{
		public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException{
			ProductVO pv=new ProductVO();
			pv.setPro_cd(rs.getString("pro_cd"));
			pv.setCategory_cd(rs.getInt("category_cd"));
			pv.setPro_name(rs.getString("pro_name"));
			pv.setPro_detail(rs.getString("pro_detail"));
			pv.setPro_img(rs.getString("pro_img"));
			pv.setPro_price(rs.getInt("pro_price"));
			pv.setSell_fl(rs.getString("sell_fl"));
			pv.setInput_date(rs.getString("input_date"));
			return pv;
		}//mapRow
	}//SelectPro	

	public ProductVO selectPro(String pro_cd) throws SQLException{
		ProductVO pv=null;
		
		String selectDetail="select * from product where pro_cd=?";
		pv=jt.queryForObject(selectDetail, new Object[] { String.valueOf(pro_cd) }, new SelectPro());

		return pv;
	}//selectPro
}
