<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%
//체크된 항목을 파라메터로 전달받기
String[] chkList=request.getParameterValues("chk");
OrderDAO od=new OrderDAO();

//체크된 항목이 없으면 되돌아가기
if(chkList==null){ %>
	<script>
		alert("항목을 체크해주세요.");
		location.href="http://localhost/shopping_mall_prj/views/user/myOrder.jsp";
	</script>
<%}

//체크된 항목이 있으면 체크된 항목 주문상태 변경(주문취소)
if(chkList!=null){
	for(int i=0; i < chkList.length; i++){
		od.updateOrderInfo(chkList[i]);
	}//end for
}//end if

%>
<!-- 다시 주문관리페이지로 이동 -->
<script>
	location.href="http://localhost/shopping_mall_prj/views/user/myOrder.jsp";
</script> --%>