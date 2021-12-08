<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>1조네 농산물</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="http://localhost/shopping_mall/common/image/favicon.png" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="http://localhost/shopping_mall/common/css/index.css" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap" rel="stylesheet">
<style>
	#title{ text-align: center; 
			margin: 30px 0; color: #D09869; 
			font-weight: bold; 
			font-family:'Sunflower', sans-serif; 
			}
#pro{
	text-decoration: none;
	color:#000;
}
</style>
<!-- Bootstrap core JS-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<!-- <script src="js/scripts.js"></script> -->
</head>
<body>
<jsp:include page="layout/header.jsp"/>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<jsp:include page="layout/footer.jsp"/>
</body>
</html>
