package kr.co.shopping_mall.service.user;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.Home_DAO;
import kr.co.shopping_mall.vo.ProductVO;

@Service
public class Home_Service {
	@Autowired
	private Home_DAO hd;
	
	public List<ProductVO> getProdList() throws SQLException{
		List<ProductVO> list=null;
		DecimalFormat fmt=new DecimalFormat("#,###,###,##0¿ø");
		
		list=hd.selectPro(0 ,0, 20);
		for(ProductVO pVO : list) {
			pVO.setPro_price_fmt(fmt.format(pVO.getPro_price()));
		}//end for
		
		return list;
	}//getProdList
}
