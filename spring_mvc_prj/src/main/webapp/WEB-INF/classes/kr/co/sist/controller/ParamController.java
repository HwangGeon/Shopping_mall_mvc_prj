package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.vo.ParamVO;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ParamController {
	@RequestMapping(value="param/param_form.do",method = GET)
	public String paramForm() {
		return "param/param_form";
	}//paramForm
	
	@RequestMapping(value="param/param_form_process.do",method= { GET,POST } )
	public String paramFormProcess(HttpServletRequest request ) {
		//post방식의 한글 처리 : request가 사용되기전 실행.
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}//end catch
		
		//파라메터 받기
		System.out.println( "요청방식 : "+ request.getMethod());
		//파라메터 값 받기
		// 유일
		System.out.println("이름 : "+ request.getParameter("name"));
		//중복 : 배열
		String[] hobby=request.getParameterValues("hobby");
		if( hobby != null ) {
			for( String value : hobby) {
				System.out.print( value+" " );
			}//end for
			System.out.println();
		}else {
			System.out.println("선택 취미 없음.");
		}//end for
		
		return "param/param_form_process";
	}//paramFormProcess
	
	@RequestMapping(value="param/param_form2.do",method = GET)
	public String paramForm2() {
		return "param/param_form2";
	}//paramForm
	
	@RequestMapping(value="param/param_form_process2.do",method= { GET,POST } )
	//web parameter는 String형으로만 전송되지만 int로 매개변수를 설정하면 Spring 에서
	//parseInt method를 사용하여 변환 후 입력해준다. 
	//String이 아닌 다른 값이 입력되면 Exception이 발생한다. 
	public String paramFormProcess2( String name, String[] hobby, 
			@RequestParam(required = false, defaultValue = "0") int age) {

		System.out.println("단일형데이터형을 사용한 파라메터 값 받기 ");
		System.out.println( "이름 : "+name);
		System.out.println( "나이 : "+ age );
		if( hobby != null ) {
			for( String value : hobby) {
				System.out.print( value+" " );
			}//end for
			System.out.println();
		}else {
			System.out.println("선택 취미 없음.");
		}//end for
		
		return "param/param_form_process2";
	}//paramFormProcess
	
	//////////////////////////11-30-2021 코드추가///////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="param/param_form3.do",method = GET)
	public String paramForm3() {
		return "param/param_form3";
	}//paramForm
	
	@RequestMapping(value="param/param_form_process3.do",method= { GET,POST } )
	public String paramFormProcess3(HttpServletRequest request, ParamVO pVO ) {
		
		pVO.setIp(request.getRemoteAddr());//아이피 변조가 불가능
		System.out.println("VO을 사용한 파라메터 값 받기 ");
		System.out.println( "이름 : "+ pVO.getName());
		System.out.println( "나이 : "+ pVO.getAge() );
		System.out.println( "접속자의 ip address: "+ pVO.getIp() );
		
		String[] hobby=pVO.getHobby();
		if( hobby != null ) {
			for( String value : hobby) {
				System.out.print( value+" " );
			}//end for
			System.out.println();
		}else {
			System.out.println("선택 취미 없음.");
		}//end for
		
		return "param/param_form_process3";
	}//paramFormProcess
}//class
