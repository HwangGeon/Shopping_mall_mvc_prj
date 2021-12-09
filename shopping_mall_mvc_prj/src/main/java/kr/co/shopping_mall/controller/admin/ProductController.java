package kr.co.shopping_mall.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import kr.co.shopping_mall.service.admin.ProductService;
import kr.co.shopping_mall.vo.ProductSearchVO;
import kr.co.shopping_mall.vo.ProductVO;

@Controller
@RequestMapping("admin/")
public class ProductController {

	@Autowired
	private ProductService ps;

	@ResponseBody
	@RequestMapping(value = "uploadSummernoteImageFile.do",method = POST)
	public String uploadSummernoteImageFile(HttpServletRequest request) {
		
		String saveDirectory = request.getSession().getServletContext().getRealPath("/common/uploadImg/pro_detail/");
		
		// 1. upload ���� ������ �ȵǾ� ������ ����
		File saveDir = new File(saveDirectory);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		// 2. �ִ�ũ�� ����
		int maxPostSize = 1024 * 1024 * 5; // 5MB  ���� byte
		
		//3. ���ڵ� ��� ����
		String encoding = "UTF-8";
		
		//4. ������å, �����̸� �浿�� ����������� �����̸� �ڿ� �ε����� ���δ�.
		//a.txt => a1.txt �� ���� �������� ����ȴ�.
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		MultipartRequest mrequest = null;
		
		try {
			mrequest = new MultipartRequest(request //MultipartRequest�� ����� ���� request
					, saveDirectory //���� ��ġ
					, maxPostSize //�ִ�ũ��
					, encoding //���ڵ� Ÿ��
					, policy); //���� ��å
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		String filesystemName = mrequest.getFilesystemName("file");

		return "http://localhost/shopping_mall/common/uploadImg/pro_detail/"+filesystemName;
	}

	@ResponseBody
	@RequestMapping(value = "deleteSummernoteImageFile.do",method = POST)
	public void deleteSummernoteImageFile(HttpServletRequest request, String fileName) {
		String saveDirectory = request.getSession().getServletContext().getRealPath("/common/uploadImg/pro_detail");

		File target = new File(saveDirectory+File.separator+fileName);

		if(target.exists()) {
			target.delete();
		} 
	}
	
	@ResponseBody
	@RequestMapping(value = "addProductProc.do", method = POST)
	public void addProductProc(HttpServletRequest request) {
		String saveDirectory = request.getSession().getServletContext().getRealPath("/common/uploadImg/pro_img/");
		int maxPostSize = 1024 * 1024 * 5;
		String encoding = "UTF-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		MultipartRequest mrequest = null;
		try {
			 mrequest = new MultipartRequest(request
					, saveDirectory 
					, maxPostSize 
					, encoding 
					, policy);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		ProductVO pVO = new ProductVO();
		pVO.setPro_name(mrequest.getParameter("pro_name")); //��ǰ��
		pVO.setPro_price(Integer.parseInt(mrequest.getParameter("pro_price"))); //��ǰ����
		pVO.setCategory_cd(Integer.parseInt(mrequest.getParameter("category_cd"))); //��ǰ�з�
		pVO.setPro_img(mrequest.getFilesystemName("pro_img")); //����� �̸�
		pVO.setPro_detail(mrequest.getParameter("pro_detail")); //��ǰ����
		
		ps.addProduct(pVO);
	}
	
	@ResponseBody
	@RequestMapping(value = "productPagenation.do",method = GET)
	public String productPagenation(ProductSearchVO psVO) {
		
		String pageCnt = ps.countSearchProduct(psVO);
		
		return pageCnt;
	}
	
	@ResponseBody
	@RequestMapping(value = "searchProduct.do",method = POST, produces="text/plain;charset=UTF-8")
	public String searchProduct(ProductSearchVO psVO) {
		
		String productList = ps.getSearchProductList(psVO);

		return productList;
	}
	
	@ResponseBody
	@RequestMapping(value = "productDashInfo.do",method = POST)
	public String productDashInfo() {
		
		JSONObject jo = ps.countProductDash();
		
		return jo.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "searchProductDash.do",method = POST, produces="text/plain;charset=UTF-8")
	public String searchProductDash(ProductSearchVO psVO) {
		
		String productList = ps.SearchProductDashList(psVO);
		
		return productList;
	}
	
	@ResponseBody
	@RequestMapping(value = "updateProductProc.do", method = POST, produces="text/plain;charset=UTF-8")
	public String updateProductProc(ProductVO pVO,String work) {
		JSONObject jo = ps.updateProduct(pVO,work);	
		return jo.toString();
	}
	
	@RequestMapping(value = "updateProductForm.do",method = GET)
	public String updateProductForm(String pro_cd, Model model) {
		
		try {
			ProductVO pVO = ps.getProductInfo(pro_cd);
			model.addAttribute("pVO",pVO);
			
		} catch (DataAccessException dae) {
			model.addAttribute("msg", "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		
		return "admin/ad_product_updateForm";	
	}
}

