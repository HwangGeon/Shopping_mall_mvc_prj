<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
	<c:if test="${empty sessionScope.admin_id}">
    <c:redirect url="loginForm.do"/>
    </c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>관리자 페이지</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="http://localhost/shopping_mall/common/css/custom.css" >
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
	integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
	crossorigin="anonymous"></script>
	
<!-- datepicker -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- /datepicker -->	

<!-- summernote 부트스트랩4와 충돌때문에 직접 다운 -->
<script src="http://localhost/shopping_mall/common/js/summernote-lite.js"></script>
<script src="http://localhost/shopping_mall/common/js/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="http://localhost/shopping_mall/common/css/summernote-lite.css">
<!-- /summernote -->

<script src="http://localhost/shopping_mall/common/js/admin.js"></script>
</head>
<!-- 네비바를 fiexd-top으로 설정했을 때 컨텐츠와 겹치는 문제 방지 -->
<body class="pt-5">
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">관리자페이지</a>
		</div>
	</nav>
	
	<!-- Page Content -->
	<div class="container-fluid h-100">
		<div class="row h-100">
			<div class="col-md-3 col-lg-2 menuTab">
				<div class="list-group mb-4 mt-4" id="menuList">
					<a href="#list-home" class="list-group-item list-group-item-action active text-left py-4 mb-2 font-weight-bold" data-toggle="list">HOME</a>
					<a href="#list-user" class="list-group-item list-group-item-action text-left py-4 mb-2 font-weight-bold" data-toggle="list">회원관리</a>
					<a href="#list-order" class="list-group-item list-group-item-action text-left py-4 mb-2 font-weight-bold" data-toggle="list">주문관리</a>
					<a href="#list-product" class="list-group-item list-group-item-action text-left py-4 mb-2 font-weight-bold" data-toggle="list">상품관리</a>
				</div>
			</div>

			<div class="col-md-9 col-lg-10 mt-4 mb-4">
					<div class="tab-content h-100">
      					<div class="tab-pane border-0 h-100 active" id="list-home"><jsp:include page="ad_home.jsp"/></div>
      					<div class="tab-pane border-0 h-100" id="list-user"><jsp:include page="ad_user.jsp"/></div>
      					<div class="tab-pane border-0 h-100" id="list-order"><jsp:include page="ad_order.jsp"/></div>
      					<div class="tab-pane border-0 h-100" id="list-product"><jsp:include page="ad_product.jsp"/></div>
      				</div>
			</div>
		</div> 
	</div> 
</body>
</html>