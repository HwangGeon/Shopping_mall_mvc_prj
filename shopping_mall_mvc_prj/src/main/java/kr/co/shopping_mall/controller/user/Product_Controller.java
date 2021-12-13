package kr.co.shopping_mall.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.sql.SQLException;
import java.util.List;

import kr.co.shopping_mall.service.user.Product_Service;
import kr.co.shopping_mall.vo.ProductPagingVO;
import kr.co.shopping_mall.vo.ProductParamVO;
import kr.co.shopping_mall.vo.ProductVO;

@Controller
public class Product_Controller {
	@Autowired
	private Product_Service ps;
	
	@RequestMapping(value="board/prod_list.do", method=GET)
	public String prodList(ProductParamVO paramVO, Model model){
		model.addAttribute("category_cd", paramVO.getCategory_cd());
		model.addAttribute("searchValue", paramVO.getSearchValue());
		
		try {
			model.addAttribute("ppVO", ps.setPaging(paramVO));
			model.addAttribute("proData", ps.getProdList(paramVO));
			model.addAttribute("proCnt", ps.getProdCount(paramVO));
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return "board/prod_list";
	}//getProdList
	
	
	@RequestMapping(value="board/prod_detail.do", method=GET)
	public String prodDetail(String pro_cd, Model model) {
		
		try {
			model.addAttribute("proData", ps.getProdDetail(pro_cd));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "board/prod_detail";
	}//prodDetail
	

}//class
