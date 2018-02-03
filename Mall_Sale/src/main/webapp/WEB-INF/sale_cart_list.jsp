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
<script type="text/javascript">
function check_item(sku_id,check){
	var shfxz = "0";
	if(check){
		shfxz = "1";
	}
	$.get("change_cart_status.do",{sku_id:sku_id,shfxz:shfxz},function(data){
		$("#cart_list_inner").html(data);
	})
}


</script>
</head>
<body>
	购物车列表：<br/>
	<div id="cart_list_inner">
		<jsp:include page="sale_cart_list_inner.jsp"></jsp:include>
	</div>
</body>
</html>