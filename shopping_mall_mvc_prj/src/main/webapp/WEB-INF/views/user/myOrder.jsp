<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="마이페이지_주문내역"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>마이페이지_주문내역</title>

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
    	/* height:50%; */
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
    	font-family: 'Sunflower', sans-serif;
    	padding-top:10px;
    }
    
    .orderInfo{ 
    	text-align: left;
    }
    .infoDiv{ 
    	border-bottom: 2px solid #D09869; 
    }
    
    #title{
    	text-align:left; 
    	color:#D09869; 
    	font-weight: bold; 
    	font-family: 'Sunflower', sans-serif; 
    	margin:10% 0 40px 0;
    }
    
    #chkStyle{ 
    	border-bottom: 2px solid #D09869; 
    	border-top: 2px solid #D09869; 
    	vertical-align: middle;
    }
    #listStyle{
    	border-bottom: 2px solid #D09869; 
    	border-top: 2px solid #D09869; 
    	vertical-align: middle;
    }
    
    #tableDiv{
    	width:100%; 
    	height: 400px; 
    	border-bottom:1px solid #DDD; 
    	overflow:auto;
    	margin-bottom:5%;
    }
    
    #cancelBtn{
    	width:22%; 
    	height:56px; 
    	background:#D09869; 
    	color:#FFFFFF; 
    	border-color:#FFFFFF; 
    	font-size:15px;
    }
    
    #backBtn{
    	width:22%; 
    	height:56px; 
    	color:#D09869;
    	border-color: #D09869; 
    	font-size:15px;
    }
    
    #emptyList{
    	text-align: center;
    	border-bottom:1px solid #FFF;
    }
    
    #colNone{
    	color:#000;
    }

</style>
<script type="text/javascript">
function goBack(){
	window.history.back();
}

$(function(){
	$("#chkAll").click(function(){
		if($("#chkAll").prop("checked")){
			$("input[name=chk]").prop("checked",true);
		}else{
			$("input[name=chk]").prop("checked",false);
		}//end else
	});//click
	
	$("#cancelBtn").click(function(){
		if(confirm("정말 취소하시겠습니까?")){			
			$("#OrderFrm").submit();
		}//end if
		return;
	});//click
	
});//ready
<c:if test="${ param.err_flag eq '1' }">
alert("Error");
</c:if>
</script>
<body>   
<jsp:include page="../layout/header.jsp"/>
	<form id="OrderFrm" method="post" action="http://localhost/shopping_mall/user/myOrder_proc.do">
        <div class="container">
        &nbsp;<div class="dl">
        	<a href="myOrder.do"><div class="dt"><h3 id="dt">주문내역</h3></div></a>
        	<a href="myInfo.do"><div class="dt"><h3 id="dt">개인정보</h3></div></a>
        </div>
        <h2 id="title">주문관리</h2>    
        <div id="tableDiv">
        <table class="table table-responsive" id="tbl-product">
            <colgroup>
                <col style="width:7%;">
				<col style="width:31%;">
				<col style="width:31%;">
				<col style="width:31%;">
            </colgroup>
            <thead>
				<tr>
				<th id="chkStyle"><input type="checkbox" id="chkAll"></th>
				<th colspan="3" id="listStyle">주문내역</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${ dataCnt ne null }">
            <c:forEach var="ord" items="${ orderData }">
            <c:if test="${ ord.ord_cd ne 'xxx' }">
            <tr>
				<td rowspan="5"><input type="checkbox" name="chk" value="${ ord.ord_cd }"
					<c:if test="${ ord.ord_stat_name ne '주문완료' }"> disabled="disabled"</c:if>
				></td>
				<td class="orderInfo" colspan="3"><a id="colNone" href="order_detail.do?ord_cd=${ ord.ord_cd }"><strong>${ ord.pro_name }</strong></a></td>
			</tr>
			<tr>
				<td class="orderInfo" colspan="3">주문번호 : ${ ord.ord_cd }</td>
			</tr>
			<tr>
				<td class="orderInfo" colspan="3">${ ord.ord_date }</td>
			</tr>
			<tr>
				<td class="orderInfo" colspan="3">${ ord.ord_price }원</td>
			</tr>
			<tr style="border-bottom: 2px solid #D09869;">
				<td class="orderInfo" colspan="3">${ ord.ord_stat_name }</td>
			</tr>
            </c:if>
            </c:forEach>
            </c:if>
            <c:if test="${ dataCnt eq null }">
            	<tr>
            		<td colspan="4" id="emptyList">주문내역이 없습니다.</td>
            	</tr>
            </c:if>
			</tbody>
        </table>
        </div>
        
        <p style="text-align:center;"><!-- 56px -->
		  <button type="button" class="btn btn-default btn-lg" id="cancelBtn">주문취소</button>
		  <button type="button" class="btn btn-default btn-lg" id="backBtn" onclick="goBack()">돌아가기</button>
		</p>
        </div>
        </form>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
