package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.sql.SQLException;
import java.util.Random;

@Controller
@RequestMapping("exception/")
public class ExceptionController {

	@RequestMapping(value="exception.do",method = GET)
	public String classNot()throws ClassNotFoundException {
		
		if(new Random().nextBoolean()) {
			throw new ClassNotFoundException("클래스가 존재하지 않는 예외");
		}//end if
		
		return "exception/result";
	}//classNot
	@RequestMapping(value="exception2.do",method = GET)
	public String query()throws SQLException {
		
		if(new Random().nextBoolean()) {
			throw new SQLException("데이터베이스를 사용하는 중 문제발생.");
		}//end if
		
		return "exception/result";
	}//query
	
	@ExceptionHandler( ClassNotFoundException.class )
	public ModelAndView classNotExceptionHandling(ClassNotFoundException cnfe) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("exception/cnfe_result");
		mav.addObject("cnfe", cnfe);
		return mav;
	}//classNotExceptionHandling
	
	@ExceptionHandler( SQLException.class )
	public ModelAndView sqlExceptionHandling(SQLException se) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("exception/cnfe_result");
		mav.addObject("cnfe", se);
		return mav;
	}//sqlExceptionHandling
	
	
}//class
