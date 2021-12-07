package kr.co.shopping_mall.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.admin.ProductDAO;
import kr.co.shopping_mall.vo.ProductVO;


@Service
public class ProductService {

	@Autowired
	private ProductDAO pd;

	public void addProduct(ProductVO pVO) {
		pd.insertProduct(pVO);
	}
}
