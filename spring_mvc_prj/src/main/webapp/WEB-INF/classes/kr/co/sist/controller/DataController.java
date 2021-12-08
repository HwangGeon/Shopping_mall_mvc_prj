package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DataController {
	
	@RequestMapping(value="send/use_request.do",method = GET)
	public String useRequest(HttpServletRequest request) {
		//발생된 데이터를 HttpServletRequest에 저장하여 View로 전달.
		
		request.setAttribute("name", "<u>하진수</u>");//Controller에서 작성된 태그가 View로 전달되어
		//<c:out>을 통해 출력되면 c:out은 tag 를 해석하지 않고 그대로 출력된다. 
		request.setAttribute("subjects", 
				new String[] {"Java SE","Oracle","JDBC","HTML","CSS","JavaScript"});
		
		return "data/use_request";
	}//useRequest
	
	@RequestMapping(value="send/use_model.do",method = GET)
	//BindingAwareModelMap
	public String useModel( Model model ) {// 개발자가 객체생성을 하지 않는다. 
		//발생된 데이터를 Model에 저장하여 View로 전달.
		
		model.addAttribute("name", "<u>안주환</u>");
		model.addAttribute("subjects", 
				new String[] {"Model사용","Java SE","Oracle","JDBC","HTML","CSS","JavaScript"});
		
		return "data/use_request";
	}//useRequest
	
	@RequestMapping(value="send/use_model_and_view.do",method = GET)
	//반환형을 ModelAndView로 설정
	public ModelAndView useModelAndView( ) {
		//발생된 데이터를 ModelAndView에 저장하여 View로 전달.
		
		//ModelAndView를 생성
		ModelAndView mav=new ModelAndView();
		//view명설정
		mav.setViewName("data/use_request");
		//값설정 : 설정된 값은 requestScope에 저장되어 전달.
		mav.addObject("name", "<u>안주환</u>");
		mav.addObject("subjects", 
				new String[] {"<strong>ModelAndView사용</strong>","Java SE","Oracle","JDBC","HTML","CSS","JavaScript"});
		
		return mav;
	}//useRequest
}//class
