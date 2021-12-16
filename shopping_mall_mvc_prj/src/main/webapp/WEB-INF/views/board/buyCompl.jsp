<%@page import="kr.co.shopping_mall.vo.ProductVO"%>
<%@page import="kr.co.shopping_mall.dao.user.Order_DAO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.sist.util.cipher.DataEncrypt"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="구매완료"
    %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>구매완료</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="http://localhost/shopping_mall/common/image/favicon.png" />
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
<style type="text/css">
	.container2{text-align:center; margin-bottom:40px;}
	h2{margin-top:100px; font-family: 'Sunflower', sans-serif; font-weight: bold; color:#D09869; margin-bottom:40px;}
	#div1{margin-bottom:20px;}
	#div2{margin-bottom:50px; font-size:18px;}
	#tbl-compl{border:1px solid #DDDDDD; border-color:#D09869; margin-bottom:20px;}
	colgroup{text-align:left;}
	tr th{text-align:center;}
	tr td{text-align:left;}
	.table>tbody>tr>td{padding:30px; font-size:16px;}
	.table>tbody>tr>th{vertical-align:middle; font-size:16px;}
	.btn1{width:100%; height:56px; background:#D09869; color:#FFFFFF; padding:1px font-size:15px;}
	.btn1:hover{color:#FFFFFF;}
	#tbl-compl tr{border-bottom:1px solid #DDDDDD;}
	#tbl-compl tr:last-of-type{border-bottom-color:#D09869;}
</style>
<c:if test="${sessionScope.user_id eq null }">
<c:redirect url="http://localhost/shopping_mall/user/login/loginForm.do?err_flag=1"/>
</c:if>
<script>
<c:if test="${ not empty msg }">
alert("${ msg }");
location.href="http://localhost/shopping_mall/board/cart_list.do";
</c:if>
</script>
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<c:catch var="e">
<form id="frm" name="frm" >
	<div class="container container2">
		<h2>주문완료</h2>
		<div id="div1">1조네 농산물을 이용해 주셔서 감사합니다.</div>
		<div id="div2">아래 계좌정보로 입금해 주시면<br/>결제 완료처리가 됩니다.</div>
		<div class="table-responsive">
			 <table class="table table-borderless" id="tbl-compl" >
				 <colgroup>
		                <col style="width: 30%;"/>
		                <col style="width: 70%;"/>
		         </colgroup>
			 	<tr>
			 		<th>입금계좌 안내</th>
			 		<td>1조네 농산물<br/>451-12-189896<br/>농협</td>
			 	</tr>
			 	<tr>
			 		<th>주문번호</th>
			 		<td>${  ordCd }</td> 
			 	</tr>
			 	<tr>
			 		<th>배송정보</th>
			 		<td>${ dVO.dv_name }<br/>${ dVO.dv_tel }<br/>${ dVO.dv_addr }</td>
			 	</tr>
			 	<tr>
			 		<th>배송메모</th>
			 		<td>${  dVO.dv_memo}</td>
			 	</tr>
		 	</table>
		 </div>
	 	<button type="button" class="btn btn-default btn-lg btn1" onclick="location.href='http://localhost/shopping_mall/user/myOrder.do'">주문내역</button>
	</div>
</form>
</c:catch>
<c:if test="${ not empty e }">
<c:redirect url="http://localhost/shopping_mall/board/cart_list.do"/>
</c:if>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html> 