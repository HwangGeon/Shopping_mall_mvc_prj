<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#searchBtn").click(function(){
		$("#searchFrm").submit();
	});//click
});//ready

function moveLogin(){
	location.href="login/loginForm.do";
}

function moveLogout(){
	location.href="http://localhost/shopping_mall/views/user/logoout_proc.do";
}

function moveMy(){
	location.href="http://localhost/shopping_mall/views/user/myOrder.do";
}

function moveCart(){
	location.href="http://localhost/shopping_mall/views/board/cart_list.do";
}
</script>
	<h1
		style="text-align: center; font-size:2.5rem; margin: 30px 0; color: #D09869; font-weight: bold; font-family: 'Sunflower', sans-serif;">
		<a href="http://localhost/shopping_mall_prj/views/index.jsp" style="text-decoration:none; color:#D09869;">1조네 농산물</a></h1>
	<% if(session.getAttribute("user_id")==null){ %>
	<input type="button" class="btn" value="로그인"
		style="position: absolute; top: 10px; right: 110px;" onclick="moveLogin()">
	<input type="button" class="btn" value="장바구니"
		style="position: absolute; top: 10px; right: 30px;" onclick="moveCart()">
	<%}else{ %>
	<input type="button" class="btn" value="로그아웃"
		style="position: absolute; top: 10px; right: 205px;" onclick="moveLogout()">
	<input type="button" class="btn" value="마이페이지"
		style="position: absolute; top: 10px; right: 110px;" onclick="moveMy()">
	<input type="button" class="btn" value="장바구니"
		style="position: absolute; top: 10px; right: 30px;" onclick="moveCart()">
	<%} %>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="http://localhost/shopping_mall/views/board/prod_list.do?category_cd=0">전체상품</a></li>
					<li class="nav-item"><a class="nav-link" href="http://localhost/shopping_mall/views/board/prod_list.do?category_cd=1">농산물</a></li>
					<li class="nav-item"><a class="nav-link" href="http://localhost/shopping_mall/views/board/prod_list.do?category_cd=2">수산물</a></li>
					<li class="nav-item"><a class="nav-link" href="http://localhost/shopping_mall/views/board/prod_list.do?category_cd=3">축산물</a></li>
				</ul>                                                      
				<form class="d-flex" id="searchFrm" action="http://localhost/shopping_mall/views/board/prod_list.do" method="get">
					<div class="input-group" style="align-items: center;">
						<input type="text" class="form-control" name="searchValue"
							style="border-radius: 0.25rem; width: 200px; height: 38px; margin-right: 5px; line-height: 62px;">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" id="searchBtn"
								style="border-radius: 0.25rem; border: 1px solid #212529; color: #000000; margin-right: 20px;">검색</button>
						</span>
					</div>
				</form>
			</div>
		</div>
	</nav>