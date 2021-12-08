package kr.co.shopping_mall.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import kr.co.shopping_mall.dao.admin.ProductDAO;
import kr.co.shopping_mall.vo.ProductSearchVO;
import kr.co.shopping_mall.vo.ProductVO;


@Service
public class ProductService {

	@Autowired
	private ProductDAO pd;

	public void addProduct(ProductVO pVO) {
		pd.insertProduct(pVO);
	}

	public String countSearchProduct(ProductSearchVO psVO) {
		
		int totalRows = pd.countSearchPro(psVO);
		int rowsPerPage = 8; //페이지당 보여줄갯수
		int pageCount = (int)Math.ceil((double)totalRows/rowsPerPage);
		
		return String.valueOf(pageCount);
	}

	public String getSearchProductList(ProductSearchVO psVO) {
		
		int index = Integer.valueOf(psVO.getIndex());
		int rowsPerPage = 8; //페이지당 보여줄갯수
		//데이터시작점
		int start = (index-1) * rowsPerPage;
		
		List<ProductVO> productList = pd.selectProductList(psVO, start, rowsPerPage);
		
		Gson gson = new Gson();
		
		return gson.toJson(productList);
	}
}
