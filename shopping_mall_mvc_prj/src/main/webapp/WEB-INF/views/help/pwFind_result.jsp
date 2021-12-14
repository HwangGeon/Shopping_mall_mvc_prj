<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" info="비밀번호 찾기 성공 페이지"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>비밀번호 찾기 성공 </title>
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
.line{margin-top: 70px; margin-left: 550px}
.lin{margin-left: 550px}
</style>
<script type="text/javascript">

</script>
<body>
	<jsp:include page="../layout/header.jsp"/>
		<hr class="line" style="border: solid 1px black; width: 800px">
		
<div style="height: 150px">
	<h1 style="text-align:center; margin : 30px; color:#D09869; font-weight: bold; font-family: 'Sunflower', sans-serif;">SEARCH</h1>
	<h2 style="text-align:center; margin : 30px; color:#D09869; font-weight: bold; font-family: 'Sunflower', sans-serif;">아이디,비밀번호 찾기</h2>
</div>
<hr class="lin" style="border: solid 1px black; width: 800px">

<h4 style="text-align:center; margin-bottom:20px; margin-top : 70px ;color:#D8D8D8; font-weight: bold; font-family: 'Sunflower', sans-serif;">임시 비밀번호가 발급되었습니다.</h4>
<h3 style="text-align:center; margin-bottom:189px; margin-top : 70px ;color:#D8D8D8; font-weight: bold; font-family: 'Sunflower', sans-serif;">${ user_pw }</h3>
	<jsp:include page="../layout/footer.jsp"/>
</body>
</html> 