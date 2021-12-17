<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" info="아이디 비밀번호 찾기"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>아이디 비밀번호 찾기</title>
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
</head>
<style type="text/css">
#title{ 
	text-align:left; color: #D09869; 
	font-weight: bold; 
	font-family: 'Sunflower', sans-serif; 
	margin:100px 0 40px 0; 
}

.subTitle{
	color: #D09869;
	font-family: 'Sunflower', sans-serif; 
	margin:0 0 20px 0;
}

.subCon{
	padding:0 0 0 40px;
}

.confirmBtn{
 	width:15%; 
    height:40px; 
    background:#D09869; 
    color:#FFFFFF; 
    border-color:#FFFFFF; 
    font-size:15px;
    margin-left:55%;
}

.colLab{
	color:#D09869;
}

.inputBox{
	width: 60%;
    height: 45px;
    border: 1px solid #ced4da;
    color: #333;
    padding-left: 1%;
}

#last{
	margin-bottom: 92px;
}

.line{
	margin-bottom:24px;
}


</style>
<script type="text/javascript">
$(function(){
	/* 아이디찾기 */
	$("#idFindBtn").click(function() {
		if($("#idName").val() == ""){
			alert("이름을 입력해주세요.");
			return;
		}
		
		if($("#idEmail").val() == ""){
			alert("이메일을 입력해주세요.");
			return;
		}
		
		$("#idFrm").submit();
	});//click
	
	/* 비밀번호찾기 */
	$("#pwFindBtn").click(function() {
		if($("#pwName").val() == ""){
			alert("이름을 입력해주세요.");
			return;
		}
		
		if($("#pwId").val() == ""){
			alert("아이디를 입력해주세요.");
			return;
		}
		
		if($("#pwEmail").val() == ""){
			alert("이메일을 입력해주세요.");
			return;
		}
		
		$("#pwFrm").submit();
	});//click
});//ready
</script>
<body>
 <jsp:include page="../layout/header.jsp"/> 
 
<div class="container">
<h2 id="title">아이디 찾기 / 비밀번호 찾기</h2>
<div class="row">
	<div class="col-md-6 subCon">
		<h4 class="subTitle">아이디 찾기</h5>
		<form id="idFrm" action="idFind_result.do" method="post">
		<div class="line"><label class="colLab" style="padding-right:5.5%">이름</label><input type="text" class="inputBox" name="user_name" placeholder="이름" id="idName"/><br/></div>
		<div class="line" id="last"><label class="colLab" style="padding-right:3%">이메일</label><input type="text" class="inputBox" name="user_email" placeholder="이메일" id="idEmail"/><br/></div>
		<button type="button" class="btn btn-default btn-lg confirmBtn" id="idFindBtn">확인</button>
		</form>
	</div>
	<div class="col-md-6 subCon">
		<h4 class="subTitle">비밀번호 찾기</h5>
		<form id="pwFrm" action="pwFind_result.do" method="post">
		<div class="line"><label class="colLab" style="padding-right:5.5%">이름</label><input type="text" class="inputBox" name="user_name" placeholder="이름" id="pwName"/><br/></div>
		<div class="line"><label class="colLab" style="padding-right:3%">아이디</label><input type="text" class="inputBox" name="user_id" placeholder="아이디" id="pwId"/><br/></div>
		<div class="line"><label class="colLab" style="padding-right:3%">이메일</label><input type="text" class="inputBox" name="user_email" placeholder="이메일" id="pwEmail"/><br/></div>
		<button type="button" class="btn btn-default btn-lg confirmBtn" id="pwFindBtn">확인</button>
		</form>
	</div>
</div>
</div>


    <jsp:include page="../layout/footer.jsp"/> 
</body>
</html>