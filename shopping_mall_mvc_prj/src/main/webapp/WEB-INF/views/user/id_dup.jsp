<%@page import="kr.co.shopping_mall.dao.user.User_DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="아이디 중복확인"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>아이디 중복확인</title>
<style type="text/css">

</style>
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
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		chkNull();
	});
	$("#user_id").keydown(function(evt){
		if(evt.which == 13){
			chkNull();
		}//end if
	}); 
});//ready
function chkNull(){
	$("ok").remove;
	$("no").remove;
	let user_id=$("#user_id").val().trim();
	
	if(user_id==""){
		alert("중복 검사할 아이디를 입력해주세요.");
		return;
	}//end if
	 var idRegExp = /^[a-zA-z0-9]{5,12}$/; //아이디 유효성 검사
     if (!idRegExp.test(user_id)) {
         alert("아이디는 영문 대소문자와 숫자 5~12자리로 입력할 수 있습니다.");
         frm.user_id.value = "";
         frm.user_id.focus();
         return;
     }
	$("#frm").submit();
}//chkNull
function sendId(user_id){
	//부모창으로 아이디 전달
	opener.window.document.frm.user_id.value="${param.user_id}";
	//자식창 닫기
	self.close();
}//sendId
</script>
</head>
<body>
<form action="id_dup_proc.do" method="get" id="frm">
<h2 style="text-align:center; color:#D09869; margin:30px 0;font-weight: bold; font-family: 'Sunflower', sans-serif;">아이디중복확인</h2>
<!--  웹 브라우저에 키 입력이 가능한 Form Control이 하나만 제공되나면
자바스크립트 코드를 정의하지 않아도 엔터를 쳤을 때 back-end로 전송된다. -->
<div class="input-group mb-3" style="align:center !important;">
	<input type="text" name="user_id" id="user_id" placeholder="아이디" style="width:300px; height:40px; margin:0 5px 10px 30px;"/>
	<!-- 자동전송을 막기위해 값전달이 되지 않는 form control을 하나 만들고 숨긴다. -->
	<input class="form-control" type="text" style="display:none;"/>
	<input type="button" class="btn btn-default btn-sm" value="중복확인" id="btn" style="color: #FFFFFF; background:#D09869; height:40px;"/><br/>
</div>
<div style="margin-left: 30px; font-size:13px;">영문 대소문자와 숫자 5~12자리로 입력할 수 있습니다.</div>
<c:if test="${ not empty param.user_id }"> 
	<!-- id라는 파라메터가 존재하면 입력된 아이디를 사용하여 DBTable에 아이디가 존재하는지 조회-->
	<div>
		<span style="color:#0D569F;font-weight:bold; margin-left:30px;">
			입력하신 <c:out value="${ param.user_id }"/> 
		</span>는
		<c:choose>
			<c:when test="${ empty user_id }">
				<span id="ok" style="color:#0D569F;font-weight:bold;">
					사용 가능합니다.
				</span>
				[<a href="javascript:sendId('${ user_id }')">사용</a>]
			</c:when>
			<c:otherwise>
				<span id="no" style="color:#D75043;font-weight:bold;">
				이미 사용중입니다.
				</span>
			</c:otherwise>
		</c:choose>
	</div>
</c:if> 
</form>
</body>
</html>