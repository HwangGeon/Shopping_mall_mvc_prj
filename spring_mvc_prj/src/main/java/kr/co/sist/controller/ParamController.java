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
		//post����� �ѱ� ó�� : request�� ���Ǳ��� ����.
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}//end catch
		
		//�Ķ���� �ޱ�
		System.out.println( "��û��� : "+ request.getMethod());
		//�Ķ���� �� �ޱ�
		// ����
		System.out.println("�̸� : "+ request.getParameter("name"));
		//�ߺ� : �迭
		String[] hobby=request.getParameterValues("hobby");
		if( hobby != null ) {
			for( String value : hobby) {
				System.out.print( value+" " );
			}//end for
			System.out.println();
		}else {
			System.out.println("���� ��� ����.");
		}//end for
		
		return "param/param_form_process";
	}//paramFormProcess
	
	@RequestMapping(value="param/param_form2.do",method = GET)
	public String paramForm2() {
		return "param/param_form2";
	}//paramForm
	
	@RequestMapping(value="param/param_form_process2.do",method= { GET,POST } )
	//web parameter�� String�����θ� ���۵����� int�� �Ű������� �����ϸ� Spring ����
	//parseInt method�� ����Ͽ� ��ȯ �� �Է����ش�. 
	//String�� �ƴ� �ٸ� ���� �ԷµǸ� Exception�� �߻��Ѵ�. 
	public String paramFormProcess2( String name, String[] hobby, 
			@RequestParam(required = false, defaultValue = "0") int age) {

		System.out.println("���������������� ����� �Ķ���� �� �ޱ� ");
		System.out.println( "�̸� : "+name);
		System.out.println( "���� : "+ age );
		if( hobby != null ) {
			for( String value : hobby) {
				System.out.print( value+" " );
			}//end for
			System.out.println();
		}else {
			System.out.println("���� ��� ����.");
		}//end for
		
		return "param/param_form_process2";
	}//paramFormProcess
	
	//////////////////////////11-30-2021 �ڵ��߰�///////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="param/param_form3.do",method = GET)
	public String paramForm3() {
		return "param/param_form3";
	}//paramForm
	
	@RequestMapping(value="param/param_form_process3.do",method= { GET,POST } )
	public String paramFormProcess3(HttpServletRequest request, ParamVO pVO ) {
		
		pVO.setIp(request.getRemoteAddr());//������ ������ �Ұ���
		System.out.println("VO�� ����� �Ķ���� �� �ޱ� ");
		System.out.println( "�̸� : "+ pVO.getName());
		System.out.println( "���� : "+ pVO.getAge() );
		System.out.println( "�������� ip address: "+ pVO.getIp() );
		
		String[] hobby=pVO.getHobby();
		if( hobby != null ) {
			for( String value : hobby) {
				System.out.print( value+" " );
			}//end for
			System.out.println();
		}else {
			System.out.println("���� ��� ����.");
		}//end for
		
		return "param/param_form_process3";
	}//paramFormProcess
}//class
