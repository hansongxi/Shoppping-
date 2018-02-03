<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function show_cart(){
		$.get("get_miniCart.do",function(data){
			$("#miniCart_list_inner").html(data);
		});
		$("#miniCart_list_inner").show();
	}
	function hide_cart(){
		$("#miniCart_list_inner").hide();
	}

</script>
</head>
<body>
	<div class="card">
			<a target="_blank" href="goto_cart_list.do" onmouseover="show_cart()" onmouseout="hide_cart()">购物车<div class="num">0</div></a>
			<!--购物车商品-->
		
			<div id="miniCart_list_inner"></div>
		</div>
</body>
</html>