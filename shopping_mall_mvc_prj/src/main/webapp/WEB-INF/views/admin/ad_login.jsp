<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" info="amdin_login"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>관리자 로그인</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
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
<style type="text/css">
.container {
	width: 500px;
	margin: 0 auto;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.input-group-prepend {
	width: 70px;
}
</style>
<script type="text/javascript">
	<c:if test="${ not empty msg }">
		alert("${ msg }");
	</c:if>
</script>
</head>
<body>
	<div class="container">
		<div class="mb-5" align="center">
			<h1>
				<strong>관리자 로그인</strong>
			</h1>
		</div>
		<form action="adminLogin.do" method="post" id="frm">
			<!-- ID입력 -->
			<div class="input-group input-group-lg mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text w-100">ID</span>
				</div>
				<input type="text" name="admin_id" class="form-control" placeholder="아이디" required>
			</div>
			<!-- PW입력 -->
			<div class="input-group input-group-lg mb-4">
				<div class="input-group-prepend">
					<span class="input-group-text w-100">PW</span>
				</div>
				<input type="password" name="admin_pw" class="form-control" placeholder="비밀번호" required>
			</div>
			<!-- 로그인 버튼 -->
			<button class="btn btn-dark btn-lg btn-block">로그인</button>
		</form>
	</div>
</body>
</html>