<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" info="user_login"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="description" content="" />
 <meta name="author" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="http://localhost/shopping_mall_prj/common/image/favicon.png" />
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
      <!-- Bootstrap icons-->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
      <!-- Core theme CSS (includes Bootstrap)-->
      <link href="css/styles.css" rel="stylesheet" />
      <style>
	@import url('https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap');
	</style>
       <!-- Bootstrap core JS-->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
      <!-- Core theme JS-->
      <script src="js/scripts.js"></script>
<style type="text/css">
.container1 {
   width: 500px;
   margin : 0 auto;
   position: absolute;
    top: 40%;
    left: 50%; 
    transform: translate(-50%, -50%); 
} 
.container2{text-align="center";}
h1{
	text-align:center; 
	margin:30px 0; 
	color:#D09869; 
	font-weight: bold; 
	font-family: 'Sunflower', sans-serif;
}
#login{
	width:100%; 
	height:56px; 
	background:#D09869; 
	color:#FFFFFF; 
	border-color:#FFFFFF; 
	font-size:15px;
	margin-bottom:11px;
} 
#login:hover{
	color:#FFFFFF; 
}
#join{
	width:100%; 
	height:56px; 
	background:#FFFFFF; 
	color:#D09869; 
	border-color:#D09869; 
	font-size:15px;
} 
.idpwFind{margin-top:10px;float:right; font-size:16px; color:#000000; text-decoration:none !important;}
.idpwFind:hover{color:#000000;}

</style>

<script type="text/javascript">
$(function(){
	$("#login").click(function(){
		$("#frm").submit();
	});
	$("#user_pw").keydown(function(key){
		if(key.keyCode==13){
			$("#frm").submit();
		}
	});
});//ready
<c:if test="${ param.err_flag eq '1' }">
alert("로그인이 필요한 페이지입니다.");//실행
</c:if>

</script>
</head>
<body>
    <div class="container container1">
      <div class="mb-5 container2">
         <h1><a href="http://localhost/shopping_mall_prj/views/index.jsp" style="text-decoration:none;color:#D09869;">1조네 농산물</a></h1>
      </div>
      <form name="frm" id="frm" action="login_proc.jsp" method="post" >
         <div class="input-group input-group-lg mb-3">
            <input type="text" name="user_id" class="form-control" placeholder="아이디" required >
         </div>
         <div class="input-group input-group-lg mb-4">
            <input type="password" name="user_pw" id="user_pw" class="form-control" placeholder="비밀번호" required>
         </div>
         <button type="button" id="login" class="btn btn-default btn-lg">로그인</button>
         <input type="button" id="join"class="btn btn-default btn-lg" value="회원가입" onclick="location.href='joinForm.jsp'"/>
         <a class="idpwFind" href="http://localhost/shopping_mall_prj/views/help/idpwFind.jsp">아이디/비밀번호 찾기</a>
      </form>
   </div> 
</body>
</html>