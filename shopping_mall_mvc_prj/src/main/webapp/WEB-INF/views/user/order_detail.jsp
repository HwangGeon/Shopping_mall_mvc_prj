<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="주문내역상세"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.user_id eq null }">
<c:redirect url="http://localhost/shopping_mall/user/login/loginForm.do?err_flag=1"/> 
</c:if> 
<c:catch var="e">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>주문내역상세</title>
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
</head>
<style>	
	h2{text-align:left; color:#D09869; font-weight: bold; font-family: 'Sunflower', sans-serif; margin:100px 0 40px 0;}
    table, th, td{ 
        text-align: center;
        height:60px;
    }
    .table>tbody>tr>td{
        vertical-align:middle;
		font-size:16px;
		 border-bottom:1px solid #F2F2F2;
    }
    .table>tbody>tr>th{
        vertical-align:middle;
		font-size:16px;
    }
	.table>tbody>tr>td{
		padding:10px 0;
	}
	.table tr:nth-child(1){border-bottom:1px solid #D09869; border-top: 1px solid #D09869;}
	#tbl-product{margin-bottom:90px; border-top:1px solid #D09869;}
	#tbl-product tr:last-of-type td{text-align:right; background:#D09869; color:#FFFFFF; font-family: 'Sunflower', sans-serif; font-size:27px; padding-right:20px;}
	colgroup{text-align:left;}
	#tbl-info tr td:last-of-type{text-align:left; padding-left:50px;} 
	.bold{font-weight:bold;}
	p{text-align:center;}
   .btn1{
 		width:22%; height:56px; background:#D09869; color:#FFFFFF; border-color: #FFFFFF; margin-bottom:50px;font-size:15px;
	}
	.btn1:hover{color:#FFFFFF;}
	
</style>
<body>   
<jsp:include page="../layout/header.jsp"/>
	<form name="frm" action="../board/buyCompl.jsp" method="post">
        <div class="container">
        <h2>주문 내역 상세</h2>         
        <div class="table-responsive">
	 		<table class="table table-borderless" id="tbl-product">
	            <colgroup>
	                <col style="width: 15%" />
	                <col style="width: 40%" />
	                <col style="width: 15%" />
	                <col style="width: 20%" />
	            </colgroup>
	            <tr>             
	                <th>주문상품</th>   
	                <th>상품명</th>
	                <th>수량</th>
	                <th>가격</th> 
	            </tr>
	            <c:forEach var="od" items="${ odList }">
	            <c:set var="i" value="${i+1 }"/>
	            <c:set var="size" value="${ odList.size() }"/>
	            <c:set var="size2" value="${ prdList.size() }"/>
	             <tr>
					<td><img src="http://localhost/shopping_mall/common/uploadImg/pro_img/${ prdList[i-1].pro_img }" style="width:100px; height:100px;"/></td>
					<td>${ prdList[i-1].pro_name }</td>
					<td>${  od.ordd_qty }</td> 
					<td>${ od.pro_price_fmt }</td>
				</tr>
			<%-- 	</c:forEach> --%>
				</c:forEach>
				<tr>
				<td id='total' colspan='4'>총 주문금액 : ${ odList[size-1].pro_price_sum_fmt}원</td>
				</tr>
	        </table>
        </div>
        <div class="table-responsive">
	        <table class="table table-borderless" id="tbl-info" style="margin-bottom:50px; border-top:1px solid #D09869;">
	        	<colgroup>
	                <col style="width: 15%;"/>
	                <col style="width: 85%;"/>
	            </colgroup>
	              <tr style="vertical-align:middle;">             
	                  <th colspan='2' style=" border-top-color: #D09869; border-bottom:1px solid #D09869;">배송정보</th> 
	              </tr>
	              <tr>
	              	<td class="bold">받는사람</td>
	              	<td>${ dVO.dv_name }</td>
	              </tr>
	              <tr style="border-bottom : none;">
	              	<td class="bold">휴대전화</td>
	              	<td>${ dVO.dv_tel}</td>
	              </tr>
	              <tr>
	              	<td class="bold">주소</td>
	              	<td>${ dVO.dv_addr }</td>
	              </tr>
	              <tr>
	              	<td class="bold">배송메모</td>
	              	<td>${ dVO.dv_memo}</td>
	              </tr>
	        </table>
        </div>
        <p style="text-align:center">
		  <button type="button" class="btn btn-default btn-lg btn1" onclick="location.href='myOrder.do'">돌아가기</button>
		</p>
	</div>
        </form>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
</c:catch>
<c:if test="${ not empty e }">
<c:redirect url="http://localhost/shopping_mall/user/myOrder.do?err_flag=1"/> 
</c:if>