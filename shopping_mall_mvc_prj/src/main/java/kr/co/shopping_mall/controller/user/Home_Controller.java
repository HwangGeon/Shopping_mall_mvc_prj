package kr.co.shopping_mall.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.shopping_mall.service.user.Home_Service;
import kr.co.shopping_mall.vo.ProductVO;

@Controller
public class Home_Controller {
	@Autowired
	private Home_Service hs;
	
	@RequestMapping(value="/index.do", method=GET)
	public String mainProd(Model model) {
		try {
			List<ProductVO> pVO=hs.getProdList();
			model.addAttribute("proData", pVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "home";
	}
}
