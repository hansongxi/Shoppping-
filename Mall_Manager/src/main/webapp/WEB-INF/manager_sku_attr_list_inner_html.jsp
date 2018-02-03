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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>尚硅谷商城</title>
<script type="text/javascript">
	function show_value(attr_id,check){
		if(check){
			$("#attr_value_"+attr_id).show();
		}else{
			$("#attr_value_"+attr_id).hide();
		}
	}

</script>
</head>
<body>
	<h2>平台选择属性</h2>
	<c:forEach items="${list_attr}" var="attr" varStatus="status">
		<input type="checkbox" value="${attr.id}" name="list_sku_attr_value[${status.index}].shxm_id" 
			onclick="show_value(${attr.id},this.checked)" />${attr.shxm_mch}
	</c:forEach >
	<hr/>
	<c:forEach items="${list_attr}" var="attr" varStatus="status">
		<div id="attr_value_${attr.id}" style="display:none;">
			<c:forEach items="${attr.list_value}" var="val">
				<input type="radio" value="${val.id}" 
					name="list_sku_attr_value[${status.index}].shxzh_id"/>
					  ${val.shxzh}${val.shxzh_mch}
			</c:forEach>
		</div>
	</c:forEach>
</body>
</html>