<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="마이페이지_개인정보"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>마이페이지_개인정보</title>

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
	
    table, th, td{ 
        text-align: center;
		font-size:16px;
        height:60px;
    }
    .table>tbody>tr>td{
        vertical-align:middle;
		font-size:16px;
    }
    .table>tbody>tr>th{
        vertical-align:middle;
		font-size:16px;
    }
    .table>tbody>tr{
    	border-bottom:1px solid #DDD;
    }
    
    .dl{
    	postion:relative;
    	width:100%;
    	/* height:10%; */
     	/* margin-top:10%; */
    	margin-bottom:10%;
    	
    }
    
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
    
    #infoText{
    	/* padding-top:10%; */
    }
    
    #gradeText{
    	font-size:15px;
    	text-align: right;
    	padding-right:3%;
    }
    
    #dt{
    	color:#D09869; 
    	font-weight: bold; 
    	font-family: 'Sunflower', sans-serif;
    	padding-top:10px;
    }
    
    #title{
    	text-align:left; 
    	color:#D09869; 
    	font-weight: bold; 
    	font-family: 'Sunflower', sans-serif; 
    }
    
    #tdStyle{
    	border-left:1px solid #DDD;
    }
    
    #passBtn{
    	width:22%; 
    	height:56px; 
    	background:#D09869; 
    	color:#FFFFFF; 
    	border-color:#FFFFFF; 
    	font-size:15px;
    }
    
    #modifyBtn{
    	width:22%; 
    	height:56px;
    	color:#D09869;
    	border-color: #D09869; 
    	font-size:15px;
    }

</style>
<script type="text/javascript">
function movePassUpdate(){
	location.href="http://localhost/shopping_mall/user/passUpdateForm.do"
}

function moveInfoUpdate(){
	location.href="http://localhost/shopping_mall/user/infoUpdateForm.do"
}
</script>
<body>   
<jsp:include page="../layout/header.jsp"/>
        <div class="container">
        &nbsp;<div class="dl">
        	<a href="myOrder.do"><div class="dt" id="order"><h3 id="dt">주문내역</h3></div></a>
        	<a href="myInfo.do"><div class="dt" id="info"><h3 id="dt">개인정보</h3></div></a>
        </div>
        
        
        <!-- 본문 -->
        <div id="infoText"><h2 id="title" style="margin:10% 0 40px 0;">회원정보</h2>
        </div>
        <div id="gradeText"><h5>회원님은 '${ userVO.grade_name }' 등급입니다.</h5>
        </div>
        <div class="table-responsive">
        <table class="table table-borderless" id="tbl-product" style="border-top:1px solid #D09869;">
        	<colgroup>
                <col style="width: 30%" />
                <col style="width: 51%" />
            </colgroup>
        	<tr>
        		<td>아이디</td>
        		<td id="tdStyle">${ userVO.user_id }</td>
        	</tr>
        	<tr>
        		<td>성명</td>
        		<td id="tdStyle">${ userVO.user_name }</td>
        	</tr>
        </table>
        </div>
        
        
        <div id="infoText"><h2 id="title" style="margin:7% 0 40px 0;">연락처정보</h2></div>
        <div>&nbsp;</div>
        <div class="table-responsive">
        <table class="table table-borderless" id="tbl-product" style="margin-bottom:90px; border-top:1px solid #D09869;">
        	<colgroup>
                <col style="width: 30%" />
                <col style="width: 51%" />
            </colgroup>
        	<tr>
        		<td>주소</td>
        		<td id="tdStyle">${ userVO.user_addr }</td>
        	</tr>
        	<tr>
        		<td>연락처</td>
        		<td id="tdStyle">${ userVO.user_tel }</td>
        	</tr>
        	<tr>
        		<td>이메일</td>
        		<td id="tdStyle">${ userVO.user_email }</td>
        	</tr>
        </table>
        </div>
        
        <p style="text-align:center; max-width:100%;">
		  <button type="button" class="btn btn-default btn-lg" id="passBtn" onclick="movePassUpdate()">비밀번호변경</button>
		  <button type="button" class="btn btn-default btn-lg" id="modifyBtn" onclick="moveInfoUpdate()">수정</button>
		</p>
        </div>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
