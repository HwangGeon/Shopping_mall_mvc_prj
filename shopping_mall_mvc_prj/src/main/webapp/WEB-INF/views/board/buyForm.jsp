<%-- <%@page import="kr.co.shopping_mall.vo.UserInfoVO"%>
<%@page import="kr.co.shopping_mall.dao.user.User_DAO"%>
<%@page import="kr.co.shopping_mall.vo.DeliveryVO"%>
<%@page import="kr.co.shopping_mall.vo.ProductVO"%>
<%@page import="kr.co.sist.util.cipher.DataDecrypt"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="구매하기"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>구매하기</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="http://localhost/shopping_mall_prj/common/image/favicon.png" />
<!--jQuery CDN-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap" rel="stylesheet">
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<%
//session을 통해 들어온 로그인 정보가 없으면 로그인페이지로 이동
String user_id=(String)session.getAttribute("user_id");
if(user_id==null){ 
	
 response.sendRedirect("http://localhost/shopping_mall_prj/views/user/loginForm.jsp?err_flag=1");
 return;
}//end if %> 
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<ProductVO> cart=null;
	
	Object obj=session.getAttribute("cart");
	if(obj==null){//세션정보가 없으면 배열을 생성
		cart = new ArrayList<ProductVO>();
	}else{ //세션정보가 있으면 강제로 캐스팅 
		cart=(ArrayList<ProductVO>)obj;
	}
	ArrayList<DeliveryVO> delivery= new ArrayList<DeliveryVO>();
	//String user_id=(String)session.getAttribute("user_id");
	User_DAO uDAO= new User_DAO();
	UserInfoVO uVO = uDAO.selectInfo(user_id);
	//개인정보 복호화
	DataDecrypt dd=new DataDecrypt("AbcdEfgHiJkLmnOpQ");
	uVO.setUser_name(dd.decryption(uVO.getUser_name()));
	uVO.setUser_tel(dd.decryption(uVO.getUser_tel()));
%>
<style type="text/css">
	form h2:nth-child(1){margin-top:100px;} 
	h2{text-align:left; color:#D09869; font-weight: bold; font-family: 'Sunflower', sans-serif; margin:0 0 40px 0;} 
	table, th, td{ 
    	text-align: center;
		height:60px;
	}  
	.table tr:nth-child(2){border-bottom : none;}
	.table>tbody>tr>th{
		vertical-align:middle;
		font-size:16px;
	 }
	 .table>tbody>tr>td{
		vertical-align:middle;
		font-size:16px;
		padding:10px 0;
	}
	.table>tbody>tr>th:first-child {
		margin-bottom:20px;
	}
	#tbl-info tr:nth-child(2)>td {
		padding-top:40px;
	}
	form table>tbody>tr:nth-child(5)>td {
		padding-bottom:40px;
	}
	.table-borderless{margin-bottom:0px;border-top:1px solid #D09869;}
	table>colgroup{text-align:left;}
	input[type="text"]{width:90%; height:45px; border:#F2F2F2 1px solid;}
	form table:first-child tr th{border-bottom:1px solid #D09869;}
	.btn-group-lg>.btn, .btn-lg {
 		width:22%; height:56px; color:#D09869; border-color: #D09869; font-size:15px;
	} 
	#tbl-product{margin-bottom:90px; border-top:1px solid #D09869;border-bottom:1px solid #D09869;}
	#tbl-product tr{border-bottom:1px solid #F2F2F2;}
	#tbl-product tr:first-of-type{border-bottom:1px solid #D09869; border-top-color: #D09869;}
	#total{text-align:right; background:#D09869; color:#FFFFFF; font-family: 'Sunflower', sans-serif; font-size:27px; padding-right:20px; padding-bottom:10px;}
	p{text-align:center;}
	p button:nth-child(2){
    	background:#D09869; color:#FFFFFF; border-color:#FFFFFF;
    } 
</style>
<script type="text/javascript">
$(function(){
	$("#dv_tel").focusout(function(){
		telValidator($(this).val()); 
	});//focusout 
	$("#buy").click(function(){
		if($("#dv_name").val()==""){
			alert("이름을 입력해 주세요.");
			$("#dv_name").focus();
			return;
		}//end if
		if($("#dv_tel").val()==""){
			alert("전화번호를 입력해 주세요.");
			return;
		}//end if
		if($("#dv_addr").val()==""){
			alert("주소를 입력해 주세요.");
			$("#dv_addr").focus();
			return;
		}//end if
		if($("#dv_memo").val()==""){
			alert("메모를 입력해 주세요.");
			$("#dv_memo").focus();
			return;
		}//end if
	
	   $("#frm").submit();
	});//click
});//ready
function telValidator(args) {
	var flag=false;
    const msg = '유효하지 않는 전화번호입니다.';
    // IE 브라우저에서는 당연히 var msg로 변경
    
    if (/^[0-9]{2,3}[0-9]{3,4}[0-9]{4}/.test(args)) {
        flag= true;
    }
    if(flag==false){
    	alert(msg);
    	$("#user_tel").val('');
    }
}//telValidator 
function goCart(){
	location.href="http://localhost/shopping_mall/views/board/cart_list.do";
}
function buy(){
	$("#frm").submit();
}
</script>
</head>
<body>   
<jsp:include page="../layout/header.jsp"/>

	<form name="frm" id="frm" method="post" action="buyCompl.jsp">
        <div class="container">
	        <h2>주문서 작성</h2>         
	        <div class="table-responsive">
		        <table class="table table-borderless" id="tbl-info">
		        	<colgroup>
		                <col style="width: 20%;"/>
		                <col style="width: 80%;"/>
		            </colgroup>
		              <tr>             
		                  <th colspan='2'>배송정보</th> 
		              </tr>
		              <tr>
		              	<td>받는사람</td>
		              	<td><input type="text" name="dv_name" id="dv_name" value="<%= uVO.getUser_name()%>"></td>
		              </tr>
		              <tr>
		              	<td>휴대전화</td>
		              	<td><input type="text" name="dv_tel" id="dv_tel" value="<%= uVO.getUser_tel()%>"></td>
		              </tr>
		              <tr>
		              	<td>주소</td>
		              	<td><input type="text" name="dv_addr" id="dv_addr" value="<%= uVO.getUser_addr()%>"></td>
		              </tr>
		              <tr>
		              	<td>배송메모</td>
		              	<td><input type="text" name="dv_memo" id="dv_memo" value="메모"></td>
		              </tr>
		        </table>
	        </div>
	        <div class="table-responsive">
		 		<table class="table table-borderless" id="tbl-product">
		            <colgroup>
		                <col style="width: 55%" />
		                <col style="width: 15%" />
		                <col style="width: 20%" />
		            </colgroup>
		            <tr>             
		                <th>상품명</th>
		                <th>수량</th>
		                <th>가격</th> 
		            </tr>
		           	<%
		            int totalSum = 0, total = 0;
		    		DecimalFormat df = new DecimalFormat("###,###,###,###,##0");
	    			for(int i = 0; i < cart.size(); i++) {
	    				ProductVO pv = cart.get(i);
	    				out.println("<tr>");
	    					out.println("<td>" + pv.getPro_name() + "</td>");
	    					out.println("<td>" + pv.getCnt() + "</td>");
	    					total = pv.getPro_price() * pv.getCnt();
	    					out.println("<td>" + df.format(total) + "</td>");
	    				out.println("</tr>");
	    				totalSum += total;
	    			}
		    				out.println("<td id='total' colspan='5'>총 주문금액 :"+df.format(totalSum)+"원</td>");			           		
		            
		    		%>	
		        </table>
	        </div>
	        <p>
			  <button type="button" class="btn btn-default btn-lg" onclick="goCart()">장바구니</button>
			  <button  type="button" class="btn btn-default btn-lg" id="buy" onclick="buy()">구매하기</button>
			</p>
        </div>
      </form> 
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
 --%>