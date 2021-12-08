package kr.co.sist.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * forward와 redirect에 대한 페이지 이동
 * @author user
 */
@Controller
public class PageMoveController {
//	@RequestMapping(value="/forward.do",method=GET)
//	public String forward() {
//		
//		return "day1129/forward";//URL이 변경되지 않는다. 
//	}//forward
	
	//요청 URL과 응답하는 JSP명이 같을 때에는 return을 사용하지 않아도 된다. 
	@RequestMapping(value="/forward.do",method=GET)
//	@RequestMapping("/forward.do") //get방식의 요청일때는  value속성과 method속성을 생략하여  사용할 수 있다.
	public void forward() {
		//System.out.println("------ forward method -------");
	}//forward
	// http://localhost/spring_mvc_prj/redirect.do
	@RequestMapping(value="/redirect.do",method=GET)
	public String redirect() {
		System.out.println("-------redirect----------");
		//redirect가 붙으면 ViewResolver를 거치지 않고 JSP를 요청하게된다. 
		//return "redirect:day1129/redirect.jsp";
		
		///////////// JSP 요청 ////////////
		return "redirect:http://localhost/spring_mvc_prj/day1129/redirect.jsp";
		///////////// HTML 요청 ////////////
//		return "redirect:http://localhost/spring_mvc_prj/day1129/redirect.html";
		///////////// do 요청 ////////////
//		return "redirect:http://localhost/spring_mvc_prj/index.do";
	}//redirect
	
	@RequestMapping(value="day1129/sub_path.do",method = GET)
	public String subPath() {
		
		return "subpath";
	}//subPath
	
	@RequestMapping(value="day1129_sub/exam.do",method = GET)
	public String requestExam() {
		
		return "day1129_sub/exam";
	}//subPath
	
	@RequestMapping(value="day1129/use_resource.do",method = GET)
	public String resource() {
		
		return "day1129/resource";
	}//subPath
	
	
	
}//class
