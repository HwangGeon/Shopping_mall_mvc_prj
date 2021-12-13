<%@page import="kr.co.shopping_mall.vo.ProductVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="장바구니"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>장바구니</title>
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
<%
	ArrayList<ProductVO> cart=null;
	
	Object obj=session.getAttribute("cart");
	
	if(obj==null){//세션정보가 없으면 배열을 생성
		cart = new ArrayList<ProductVO>();
	}else{ //세션정보가 있으면 강제로 캐스팅 
		cart=(ArrayList<ProductVO>)obj;
	}
%>
</head>
<style>
	h2{
		text-align:left; color:#D09869; font-weight: bold; 
		font-family: 'Sunflower', sans-serif; margin:100px 0 40px 0;
	}
    table, th, td{ 
        text-align: center;
		font-size:16px;
        height:60px;
    }
    .table>tbody>tr>td{
        vertical-align:middle;
		font-size:16px;
		border-bottom:1px solid #F2F2F2;
    }
    .table>tbody>tr>th{
        vertical-align:middle;
		font-size:16px;
    }
    .table>tbody>tr:nth-child(1){
       border-bottom:1px solid #D09869;
    }
 	#total{
	 	text-align:right; background:#D09869; color:#FFFFFF; font-family: 'Sunflower', sans-serif; font-size:27px;
    }
    #tbl-product{margin-bottom:90px; border-top:1px solid #D09869;}
    p{text-align:center;}
   #btnDel{border: 1px solid #D09869; color: #D09869; background: #FFFFFF; width:60px;height:40px;}
    .btn2 {
 		width:22%; height:56px; color:#D09869; border-color: #D09869; font-size:15px;
	}
	.btn2:hover{color:#D09869;}
   .btn3{
    	background:#D09869; color:#FFFFFF; border-color:#FFFFFF;
    }
    .btn3:hover{color:#FFFFFF;}
</style>
<script type="text/javascript">
/* function selectAll(selectAll)  {
	  const checkboxes 
	       = document.getElementsByName('item');
	  
	  checkboxes.forEach((checkbox) => {
	    checkbox.checked = selectAll.checked;
	  })
	} */

function fnGo(){
	location.href="../index.jsp";
}
function fnBuy(){
	if(<%=cart.size()%>==0){
		alert("장바구니가 비어 있습니다.");
		return;
	}else{
	$("#frm").submit();
	}
}

function cartRemove( pro_cd, pro_name ){
	//입력받아온 상품코드를 hidden form 에 넣습니다.
	if(confirm("["+pro_name+"] 을(를) 장바구니에서 삭제하시겠습니까?")){
		$("#pro_cd").val( pro_cd );
		$("#cartDelFrm").submit();
		
	}//end if
}//cartRemove
</script>
<body>
<jsp:include page="../layout/header.jsp"/>
	<form name="frm" id="frm" action="buyForm.jsp" method="post">
        <div class="container">
	        <h2>장바구니</h2>    
	        <div class="table-responsive">
		        <table class="table table-borderless" id="tbl-product">
		            <colgroup>
		                <col style="width: 20%" />
		                <col style="width: 35%" />
		                <col style="width: 10%" />
		                <col style="width: 10%" />
		                <col style="width: 15%" />
		            </colgroup>
		            <tr>             
		                <!-- <th><input type="checkbox" name="product" onclick="selectAll(this)"></th> -->   
		                <th></th>   
		                <th>상품명</th>
		                <th>수량</th>
		                <th>가격</th>
		                <th></th>
		            </tr>
		            <% 
		            if(cart.size()==0){
		            	out.println("<tr>");
						out.println("<td colspan= '5'>");
							out.println("장바구니에 담긴 상품이 없습니다.");
						out.println("</td>");
					out.println("</tr>");    
		            } else {
		    			int totalSum = 0, total = 0;
		    			DecimalFormat df = new DecimalFormat("###,###,###,##0");
		    			for(int i = 0; i < cart.size(); i++) {
		    				ProductVO pv = cart.get(i);
		    				out.println("<tr>");
		    					/* out.println("<td><input type='checkbox' name='item' value='"+pv.getPro_cd()+"'></td>"); */
		    					out.println("<td><img src='http://localhost/shopping_mall_prj/common/uploadImg/pro_img/" + pv.getPro_img() + "' width='100' height='100'></td>");
		    					out.println("<td>" + pv.getPro_name() + "</td>");
		    					out.println("<td>" + pv.getCnt() + "</td>");
		    					total = pv.getPro_price() * pv.getCnt();
		    					out.println("<td>" + df.format(total) + "</td>");
		    					out.println("<td><input type='button' value='삭제' id='btnDel' onclick='cartRemove(\""+ pv.getPro_cd()+"\",\""+pv.getPro_name()+"\")'/></td>");
		    					
		    				
		    				out.println("</tr>");
		    				totalSum += total;
		    			}
		    				out.println("<td id='total' colspan='5'>총 주문금액 :"+df.format(totalSum)+"원</td>");			           		
		            }
		    		%>	
					
		        </table>
        </div>
        <p>
		  <!-- <button class="btn btn-default btn-lg btn2" id="delete">삭제하기</button> -->
		  <button type="button" class="btn btn-default btn-lg btn2" onclick='fnGo()'>쇼핑하기</button>
		  <button type="button" class="btn btn-default btn-lg btn2 btn3" id="buy" onclick='fnBuy()'>구매하기</button>
		</p>
        </div>
        </form>
        <form action="removeCart.jsp" method="post" id="cartDelFrm">
         <input type="hidden" name="pro_cd" id="pro_cd">
        </form>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
