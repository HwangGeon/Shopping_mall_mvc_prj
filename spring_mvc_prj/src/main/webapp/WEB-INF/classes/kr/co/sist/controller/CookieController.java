package kr.co.sist.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cookie/")
public class CookieController {

	@RequestMapping(value="cookie_set.do",method = GET)
	public String addCookie(HttpServletResponse response) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		//1.쿠키생성
		Cookie nameCookie=new Cookie("name", "이주안");
		Cookie dateCookie=new Cookie("date", sdf.format(new Date()));
		
		//2. 생존시간설정.
		nameCookie.setMaxAge(60*60*24*1);//1일유지
		dateCookie.setMaxAge(60*60*24*1);//1일유지
		
		//3. 쿠키심기
		response.addCookie(nameCookie);
		response.addCookie(dateCookie);
		
		return "cookie/cookie_result";
	}//addCookie
	
	@RequestMapping(value="cookie_get.do",method=GET)
	public String getCookie(HttpServletRequest request, Model model) {
		
		//쿠키를 읽어 Model interface에 설정.
		Cookie[] cookies=request.getCookies();
		
		if(cookies != null) {//읽어들인 쿠키가 존재한다.
			for( Cookie cookie : cookies) {
				if("name".equals(cookie.getName())) {
					model.addAttribute("req_name", cookie.getValue());
				}//end if
				if("date".equals(cookie.getName())) {
					model.addAttribute("req_date", cookie.getValue());
				}//end if
			}//end for
			
		}//end else
		
		return "cookie/read_cookie";
	}//getCookie
	
	@RequestMapping(value="cookie_value_get.do",method=GET)
	public String getCookieValue(@CookieValue(value="name",defaultValue = "익명") String name,
			@CookieValue(value="date",defaultValue ="none" ) String date, Model model) {
		
		model.addAttribute("req_name", "cookie_value_get : "+name);
		model.addAttribute("req_date", "cookie_value_get : "+date);
		
		return "cookie/read_cookie";
	}//getCookieValue

}//class








