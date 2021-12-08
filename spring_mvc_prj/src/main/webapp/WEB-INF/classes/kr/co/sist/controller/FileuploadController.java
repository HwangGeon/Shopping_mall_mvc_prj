package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.File;
import java.io.IOError;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileuploadController {
	@RequestMapping(value="fileup/file_upload_frm.do", method =GET )
	public String uploadForm() {
		return "fileupload/upload_form";	
	}
	@RequestMapping(value="fileup/upload_process.do", method =POST )
	public String uploadProcess(HttpServletRequest request, Model model)throws IOException {
		
		File saveDir=new File("E:/dev/workspace_spring/spring_mvc_prj/src/main/webapp/upload");
		int maxSize=1024*1024*30;
		MultipartRequest mr=new MultipartRequest(request, saveDir.getAbsolutePath(), 
					maxSize,"UTF-8",new DefaultFileRenamePolicy());
		//Web Parameter는 enctype이 변경되었기 때문에 HttpServletRequest는 받을 수 없고
		// MultipartRequeset를 사용하여 받는다.
		model.addAttribute("user", mr.getParameter("name"));
		model.addAttribute("fileName", mr.getOriginalFileName("upfile") );
		
		return "fileupload/upload_result";	
	}
	
	@ExceptionHandler(IOException.class)
	public ModelAndView uploadError( IOException ie ) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("exception/cnfe_result");
		mav.addObject("cnfe", ie);
		return mav;
	}
	
}
