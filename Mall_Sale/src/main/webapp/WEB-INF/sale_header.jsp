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
<link rel="stylesheet" type="text/css" href="css/css.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function (){
		var yh_nch = getMyCookie("yh_nch");
		$("#yh_nch").text(yh_nch);
	});
	
	function getMyCookie(key){
		var val = "";
		
		/*正则表达式表示空格  */
		var cookies =document.cookie.replace(/\s/,"");
		var cookie_array = cookies.split(";");
		
		for(i=0;i<cookie_array.length;i++){
			cookie = cookie_array[i].split("=");
			if(cookie[0] == key){
				val = cookie[1];
			}
		}
		return decodeURIComponent(val) ;
	}

</script>
</head>
<body>

	<div class="top">
		<div class="top_text">
			<c:if test="${empty user}">
				<a href="goto_sale_login.do">
				<span style="color:red;" id="yh_nch">周润发</span>请登录</a> 
				<a href="javascript:;">注册</a> 
			</c:if>
			<c:if test="${not empty user}">
				<a href="#">欢迎${user.yh_nch}回来!!!!</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="goto_sale_logout.do">注销</a>
			</c:if>
		</div>
	</div>
	<div class="top_img">
		<img src="./images/top_img.jpg" alt="">
	</div>
	
</body>
</html>