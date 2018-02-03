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
<title>商城</title>
</head>
<body>
	<c:forEach items="${list_cart}" var="cart">
		<img width="70px" src="upload/image/${cart.shp_tp}" /> 
		<input type="checkbox" onclick="check_item(${cart.sku_id},checked)" 
			${cart.shfxz=="1"?"checked":""} />${cart.shfxz}
		${cart.sku_mch}
		${cart.sku_jg}
		${cart.tjshl}
		<br>
	</c:forEach>
	总金额：${sum}
</body>
</html>