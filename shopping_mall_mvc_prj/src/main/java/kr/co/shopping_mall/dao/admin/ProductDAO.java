package kr.co.shopping_mall.dao.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.shopping_mall.vo.ProductVO;

@Repository
public class ProductDAO {

	@Autowired(required = false)
	private JdbcTemplate jt;

	public void insertProduct(ProductVO pVO) throws DataAccessException  {
		StringBuilder insertProduct = new StringBuilder();
		insertProduct.append(" insert into product(pro_cd, category_cd, pro_name, pro_detail, pro_img, pro_price) ")
					 .append(" values(concat('P_',lpad(pro_cd_seq.nextval,10,'0')),?,?,?,?,?)" );
		jt.update(insertProduct.toString(), pVO.getCategory_cd(), pVO.getPro_name(), pVO.getPro_detail(), pVO.getPro_img(),
				pVO.getPro_price());
	}
}
