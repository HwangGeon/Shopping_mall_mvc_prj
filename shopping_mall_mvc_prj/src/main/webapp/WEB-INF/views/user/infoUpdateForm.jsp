<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" info="회원정보수정"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원정보수정</title>
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
<c:if test="${sessionScope.user_id eq null }">
<c:redirect url="http://localhost/shopping_mall/user/login/loginForm.do?err_flag=1"/>
</c:if> 
<style type="text/css">
.col-auto{width : 100px}
 .dt{
    	width:50%;
    	float:left;
    	z-index:9;
    	postion:relative;
    	text-align:center;
    	background:#FFF;
    	padding-bottom:1%;
    	padding-top:1%;
    	color:#D09869; 
    	border:1px solid #D09869; 
    	margin-bottom:3%;
    }
   #dt{
    	color:#D09869; 
    	font-weight: bold; 
    	padding-top:4px;
    	font-family: 'Sunflower', sans-serif; 
    }
    .dl{
    	postion:relative;
    	width:70%;
    	margin : auto;
    	margin-bottom: 6%;
    	
    }
    #infoUpdateBtn{
    	width:15%; 
    	height:56px; 
    	background:#D09869; 
    	color:#FFFFFF; 
    	border-color:#FFFFFF; 
    	font-size:15px;
    }
    #outBtn{
    	width:15%; 
    	height:56px; 
    	background:#D09869; 
    	color:#FFFFFF; 
    	border-color:#FFFFFF; 
    	font-size:15px;
    }
    
    #backBtn{
    	width:15%; 
    	height:56px; 
    	color:#D09869;
    	border-color: #D09869; 
    	font-size:15px;
    }

    #title{
    	text-align:center; 
    	color:#D09869; 
    	font-weight: bold; 
    	font-family: 'Sunflower', sans-serif; 
    	margin:0 0 40px 0;
    }
    
 	.container3 {margin-bottom: 24px !important;}
 	.passInput{width:60%; height:45px; border:1px solid #ced4da; color:#333; padding-left:1%;}
</style>
<script type="text/javascript">
$(function(){
	$("#infoUpdateBtn").click(function(){
		if($("#user_pw").val() == ""){
			alert("비밀번호는 필수입력입니다.");
			return;
		}//end if
		
		$("#infoUpdateFrm").submit();
	});//click
});//ready
function userDelete(user_id){
	$("#user_id").val( user_id );
	$("#deleteFrm").submit();
}

function goBack(){
	location.href="http://localhost/shopping_mall/user/myInfo.do";
}
</script>
<body>
	<jsp:include page="../layout/header.jsp"/>
     &nbsp;<div class="dl" >
        	<a href="http://localhost/shopping_mall/user/myOrder.do"><div class="dt" id="order"><h3 id="dt">주문내역</h3></div></a>
        	<a href="http://localhost/shopping_mall/user/myInfo.do"><div class="dt" id="info"><h3 id="dt">개인정보</h3></div></a>
        </div>

     <form id="infoUpdateFrm" action="http://localhost/shopping_mall/user/infoUpdateCompl.do" method="post">
		<div align="center" style="color: #D09869; margin-bottom: 5%;">
			<div style="width: 30%;">
			<h2 id="title">회원정보 수정</h2>
				<div class="container3">
					<label style="padding-right: 7%;">아이디</label> <input
						type="text" class="passInput" readonly="readonly" placeholder="${ uVO.user_id }"/>
				</div>
				<div class="container3">
					<label style="padding-right: 4%;">비밀번호</label> <input
						type="password" class="passInput" id="user_pw" name="user_pw"/>
				</div>
				<div class="container3">
					<label style="padding-right: 9.5%;">이름</label> <input
						type="text" class="passInput" readonly="readonly" placeholder="${ uVO.user_name }"/>
				</div>
				<div class="container3">
					<label style="padding-right: 1.5%;">휴대폰번호</label> <input
						type="text" class="passInput" readonly="readonly" placeholder="${ uVO.user_tel }"/>
				</div>
				<div class="container3">
					<label style="padding-right: 7%;">이메일</label> <input
						type="text" class="passInput" name="user_email" value="${ uVO.user_email }"/>
				</div>
				<div class="container3">
					<label style="padding-right: 9.5%;">주소</label> <input
						type="text" class="passInput" name="user_addr" value="${ uVO.user_addr }"/>
				</div>
			</div>
		</div>

     
     <p style="text-align:center;"><!-- 56px -->
		 <button type="button" class="btn btn-default btn-lg" id="outBtn" onclick="userDelete('user_id')">회원탈퇴</button>
		 <button type="button" class="btn btn-default btn-lg" id="infoUpdateBtn">수정완료</button>
		 <button type="button" class="btn btn-default btn-lg" id="backBtn" onclick="goBack()">돌아가기</button>
	</p>
     </form>
	   <form action="member/deleteForm.do" method="post" id="deleteFrm">
         <input type="hidden" name="user_id" id="user_id">
        </form>
	<jsp:include page="../layout/footer.jsp"/>
</body>
</html>