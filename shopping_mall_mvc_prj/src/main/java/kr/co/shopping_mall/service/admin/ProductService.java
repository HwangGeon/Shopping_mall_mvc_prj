package kr.co.shopping_mall.service.admin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	public JSONObject countHomeDash() {
		
		int countAll = pd.countProDashData("a");
		int countSellY = pd.countProDashData("y");
		int countSellN = pd.countProDashData("n");
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("countAll", countAll);
		hm.put("countSellY", countSellY);
		hm.put("countSellN", countSellN);
		
		return new JSONObject(hm);
	}

	public String SearchProductDashList(ProductSearchVO psVO) {
		
		int index = Integer.valueOf(psVO.getIndex());
		int rowsPerPage = 8; //페이지당 보여줄갯수
		//데이터시작점
		int start = (index-1) * rowsPerPage;

		List<ProductVO> proList = pd.proDashSearch(psVO.getFlag(), start, rowsPerPage);

		Gson gson = new Gson();	
		return gson.toJson(proList);
	}

	public ProductVO getProductInfo(String pro_cd) throws DataAccessException{
		
		ProductVO pVO = pd.selectProductInfo(pro_cd);
		
		return pVO;
	}

	public JSONObject updateProduct(ProductVO pVO, String work) {
			
		HashMap<String, String> hm = new HashMap<String, String>();
		String flag = "성공";
		
		try {
				if(work.equals("update")) {
					pd.updateProduct(pVO);
				} 
				
				if(work.equals("delete")) {
					pd.deleteProduct(pVO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				flag = "실패";
			}
		
		hm.put("flag", flag);
		
		return new JSONObject(hm);
	}
}
