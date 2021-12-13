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
		<div id="carouselExampleIndicators" class="carousel slide"
			data-bs-ride="carousel">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="http://localhost/shopping_mall/common/image/carousel1.png" class="d-block w-100" alt="..."
						style="height:500px;">
				</div>
				<div class="carousel-item">
					<img src="http://localhost/shopping_mall/common/image/carousel2.png" class="d-block w-100" alt="..." style="height:500px;">
				</div>
				<div class="carousel-item">
					<img src="http://localhost/shopping_mall/common/image/carousel3.png" class="d-block w-100" alt="..." style="height:500px;">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
<!-- 	</div>
</header> -->
<!-- Section-->
<section class="py-5">
	<h2 id="title">전체상품</h2>
	<div class="container px-4 px-lg-5 mt-5">
		<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-left">
			<c:forEach var="pro" items="${ proData }">
			<div class="col mb-5">
			<a href="http://localhost/shopping_mall/board/prod_detail.do?pro_cd=${ pro.pro_cd }" id="pro">
				<div class="card h-100">
					<!-- Product image-->
					<img class="card-img-top"
						src="http://localhost/shopping_mall/common/uploadImg/pro_img/${ pro.pro_img }" style="width:100%; height:300px;" />
						<!-- src="http://placehold.it/550X500" alt="..." /> -->
					<!-- Product details-->
					<div class="card-body p-4">
						<div class="text-center">
							<!-- Product name-->
							<h5 class="fw-bolder"><c:out value="${ pro.pro_name }"/></h5>
							<!-- Product price-->
							<c:out value="${ pro.pro_price_fmt }"/>
						</div>
					</div>
				</div>
				</a>
			</div>
			</c:forEach>
			
		</div>
	</div>
</section>
<!-- advertisement-->
<section class="bg-dark py-5">
	<div class="container px-4 px-lg-5 my-5">
		<div class="text-center text-white">
			<h1 class="display-4 fw-bolder">광고</h1>
			<p class="lead fw-normal text-white-50 mb-0">광고</p>
		</div>
	</div>
</section>
<jsp:include page="layout/footer.jsp"/>
</body>
</html>
