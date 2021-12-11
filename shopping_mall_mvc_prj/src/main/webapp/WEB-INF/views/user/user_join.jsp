<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" info="회원가입"%>
   	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="description" content="" />
 <meta name="author" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>
  
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
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="http://localhost/shopping_mall/common/image/favicon.png" />
    <!-- font -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap" rel="stylesheet">
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <style>

	</style>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
<style type="text/css">
	*{margin: 0;padding: 0;box-sizing: border-box}
	body{background-color: #f7f7f7;}
	h1{text-align:center; color:#D09869; margin:30px 0;font-weight: bold; font-family: 'Sunflower', sans-serif;}
	h6{text-align:left; color: #888; margin:10px 10px; font-weight:bold;}
	#user_id{width:60%;}
	ul{width:470px;}
	li{list-style: none}
	a{text-decoration: none;}
	.clearfix::after{content: "";display: block;clear: both;}
	.container1 {
		width: 500px;
		margin : 0 auto;
		position: absolute;
	 	top: 700px;
	    left: 50%; 
	    transform: translate(-50%, -50%); 
	} 
	.container2 {margin-bottom: 25px !important;}
	.container3 {margin-bottom: 24px !important;}
	#joinForm{width: 460px;margin: 0 auto;}
	ul.join_box{border: 1px solid #ddd;background-color: #fff;}
	.checkBox,.checkBox>ul{position: relative;}
	.checkBox>ul>li{float: left;}
	.checkBox>ul>li:first-child{width: 85%;padding: 15px;font-weight: 600;color: #888;}
	.checkBox>ul>li:nth-child(2){position: absolute; top: 50%; right: 30px;margin-top: -12px;} 
	.checkBox div{overflow:scroll; width: 96%;height: 90px; margin: 0 2%;background-color: #f7f7f7;color: #888; border: none;} 
	.btn1{background:#D09869; color:#FFFFFF; height:56px; border-color :#D09869 ;margin-bottom:50px; font-size:15px;}
	.btn1:hover{color:#FFFFFF;}
</style>

<script type="text/javascript">
$(function(){
	$("#dupId").click(function(){
		
		//새 창의 크기
		cw=500;
		ch=500;
		//스크린의 크기
		sw=screen.availWidth;
		sh=screen.availHeight;
		//열 창의 포지션
		px=(sw-cw)/2;
		py=(sh-ch)/2;
		window.open("http://localhost/shopping_mall/user/member/id_dup.do","dup","left="+px+", top="+py+", width="+cw+", height="+ch);
	});//click
	
	$("#user_pw2").focusout(function(){
		pwValidator($(this).val()); 
	});//focusout
	$("#user_tel").focusout(function(){
		telValidator($(this).val()); 
	});//focusout 
	$("#btn1").click(function(){
		if($("#user_id").val()==""){
			alert("아이디를 입력해주세요.");
			$("#user_id").focus();
			return;
		}//end if
		if($("#user_pw1").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#user_pw1").focus();
			return;
		}//end if
		if($("#user_pw2").val()==""){
			alert("비밀번호 확인을 입력해주세요.");
			$("#user_pw2").focus();
			return;
		}//end if
		if($("#user_name").val()==""){
			alert("이름을 입력해주세요.");
			$("#user_name").focus();
			return;
		}//end if
		if($("#user_tel").val()==""){
			alert("전화번호를 입력해주세요.");
			return;
		}//end if
		if($("#user_email").val()==""){
			alert("이메일을 입력해주세요.");
			$("#user_email").focus();
			return;
		}//end if
		if($("#user_addr").val()==""){
			alert("주소를 입력해주세요.");
			$("#user_addr").focus();
			return;
		}//end if
		if(!$("input:checked[id='chkReq1']").is(":checked")){
			alert("필수 항목에 모두 동의해주세요.");
			return;
		}//end if
		if(!$("input:checked[id='chkReq2']").is(":checked")){
			alert("필수 항목에 모두 동의해주세요");
			return;
		}//end if
	   $("#frm").submit();
	});//click
	$("#allCheck").click(function(){ 
		if($("#allCheck").prop("checked")) { 
			 $("input[type=checkbox]").prop("checked",true);
		 } else { $("input[type=checkbox]").prop("checked",false); 
		 } 
	});//click
	//전체 체크박스 선택 중 체크박스 하나를 풀었을 때 '전체' 체크해제
	$(".check").click(function(){
		if($("input[name='check']:checked").length==4) {
			$("#allCheck").prop("checked", true);
		}else {
			$("#allCheck").prop("checked", false);
		}
	});//click
});//ready

function pwValidator(args) {
	var flag=false;
    const msg = '비밀번호가 일치하지 않습니다.';
    if($("#user_pw1").val()!=$("#user_pw2").val()){
	      alert(msg);
	      $('#user_pw1').focus();
	      $('#user_pw2').val('');
	      return;
	    }//end if
	}//pwValidator
function telValidator(args) {
	var flag=false;
    const msg = '유효하지 않는 전화번호입니다.';
    // IE 브라우저에서는 당연히 var msg로 변경
    
    if (/^[0-9]{2,3}[0-9]{3,4}[0-9]{4}/.test(args)) {
        flag= true;
    }
    if(flag==false){
    	alert(msg);
    	$("#dv_tel").val('');
    }
}//telValidator 
<c:if test="${ not empty msg }">
alert("${ msg }");
</c:if> 

</script>
</head>
<body>
    <div class="container container1">
      <div class="mb-5 container2">
         <h1><a href="http://localhost/shopping_mall/index.do" style="text-decoration:none; color:#D09869;">1조네 농산물</a></h1>
         <h6>회원정보를 입력해주세요</h6>
      </div>
      <form name="frm" id="frm"  action="userJoin.do"  method="post">
         <div class="input-group input-group-lg mb-3 container3">
            <input type="text" name="user_id" id="user_id" class="form-control" required readonly="readonly" placeholder="아이디">
            <input type="button" class="form-control" value="중복확인" id="dupId">
         </div>
         <div class="input-group input-group-lg mb-4">
            <input type="password" name="user_pw" class="form-control" id="user_pw1" placeholder="비밀번호">
         </div>
         <div class="input-group input-group-lg mb-4">
            <input type="password" name="user_pw" class="form-control" id="user_pw2" placeholder="비밀번호 확인">
         </div>
         <div class="input-group input-group-lg mb-4">
            <input type="text" name="user_name" class="form-control" id="user_name" placeholder="이름">
         </div>
         <div class="input-group input-group-lg mb-4">
            <input type="text" name="user_tel" class="form-control"  id="user_tel" placeholder="전화번호 ( '-' 제외 하고 입력)">
         </div>
         <div class="input-group input-group-lg mb-4">
            <input type="email" name="user_email" class="form-control" id="user_email" placeholder="이메일">
         </div>
         <div class="input-group input-group-lg mb-4">
            <input type="text" name="user_addr" class="form-control"  id="user_addr" placeholder="주소">
         </div>
         <div class="input-group input-group-lg mb-4">
            <ul class="join_box">
                <li class="checkBox check01">
                <h6 style="">서비스 약관에 동의해주세요.</h6> 
                    <ul class="clearfix">
                        <li>모두 동의합니다. 
                        </li>
                        <li class="checkAllBtn">
                            <input type="checkbox" name="checkAll" id="allCheck"  >
                        </li>
                    </ul>
                </li>
                <li class="checkBox check02">
                    <ul class="clearfix">
                        <li>이용약관 동의(필수)</li>
                        <li class="checkBtn">
                            <input type="checkbox" name="check" class="check" id="chkReq1"> 
                        </li>
                    </ul>
                    <div>여러분을 환영합니다.
1조네 농산물 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 1조네 농산물 서비스의 이용과 관련하여 1조네 농산물 서비스를 제공하는 1조네 농산물 주식회사(이하 ‘1조네 농산물’)와 이를 이용하는 1조네 농산물 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 1조네 농산물 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
			       </div>
                </li>
                <li class="checkBox check03">
                    <ul class="clearfix">
                        <li>개인정보 수집 및 이용에 대한 안내(필수)</li>
                        <li class="checkBtn">
                            <input type="checkbox" name="check" class="check"  id="chkReq2">
                        </li>
                    </ul>
 
                    <div>여러분을 환영합니다.
1조네 농산물 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 1조네 농산물 서비스의 이용과 관련하여 1조네 농산물 서비스를 제공하는 1조네 농산물 주식회사(이하 ‘1조네 농산물’)와 이를 이용하는 1조네 농산물 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 1조네 농산물 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
			       </div>
                </li>
                <li class="checkBox check03">
                    <ul class="clearfix">
                        <li>위치정보 이용약관 동의(선택)</li>
                        <li class="checkBtn">
                            <input type="checkbox"  class="check"  name="check">
                        </li>
                    </ul>
 
          	        <div>여러분을 환영합니다.
1조네 농산물 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 1조네 농산물 서비스의 이용과 관련하여 1조네 농산물 서비스를 제공하는 1조네 농산물 주식회사(이하 ‘1조네 농산물’)와 이를 이용하는 1조네 농산물 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 1조네 농산물 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
			       </div>
                </li>
                <li class="checkBox check04">
                    <ul class="clearfix">
                        <li>이벤트 등 프로모션 알림 메일 수신(선택)</li>
                        <li class="checkBtn">
                            <input type="checkbox"  class="check"  name="check">
                        </li>
                    </ul>
 
                </li>
            </ul>
         </div>
         <button type="button" class="btn btn-default btn-lg btn-block btn1" id="btn1" >동의하고 가입하기</button>
</form>
</div>
</body>
</html>
