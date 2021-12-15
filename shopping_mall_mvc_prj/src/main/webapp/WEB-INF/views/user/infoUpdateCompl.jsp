<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" info="회원정보수정완료"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원정보수정완료</title>
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
#wrap{width:100%; margin:0 auto; margin-top:100px; text-align:center; font-size:18px;}
img{width : 150px; display:block; margin:0 auto; align:center; margin-top: 40px; margin-bottom: 30px}
.join{margin-bottom: 60px; font-family: 'Sunflower', sans-serif; color:#D09869; margin-bottom:40px;}
.msg{margin-bottom:50px;}
hr{margin:0 auto; width:50%; border: 1px solid #000000; width : 800px; margin-bottom:50px; border-color:#D09869;}
#btn{width:15%; height:50px; background:#0099FF; ;color:#FFFFFF; font-size:16px; font-weight:bold; margin-bottom: 40px}
</style>
<script type="text/javascript">
function moveMain(){
	location.href="http://localhost/shopping_mall_prj/views/index.jsp";
}
</script>
<body>
	<jsp:include page="../layout/header.jsp"/>
	
		<div id="wrap" style="color:#D09869; font-weight: bold; font-family: 'Sunflower', sans-serif;">                          
			<img src="http://localhost/shopping_mall_prj/common/image/check.png"><br/>
			<div>
				<h2 class="join">회원정보가 수정되었습니다.</h2>
				<div class="msg">변경된 사항은 마이페이지에서 확인 하실 수 있습니다.</div>
				<hr>
			</div>
			<form action="frm">
		<div>
			  <button id="btn" type="button" class="btn btn-default btn-lg" style="background-color:#D09869; color : #FFFFFF; font-weight: bold; font-family: 'Sunflower', sans-serif;" onclick="moveMain()">첫화면</button>
		</div>
			</form>
		</div>
	<jsp:include page="../layout/footer.jsp"/>
</body>
</html>