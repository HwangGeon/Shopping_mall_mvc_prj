package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodController {
	@RequestMapping(value = "/get.do",method = RequestMethod.GET)
	public String getMethod() {
		return "get";
	}//getMethod
	
	@RequestMapping(value="/post.do",method = RequestMethod.POST)
	public String postMethod() {
		return "post";
	}//postMethod
	
	@RequestMapping(value="/post_get.do",method= {RequestMethod.GET,RequestMethod.POST})
	public String getPostMethod() {
		return "chgMethod";
	}
	
}//class
