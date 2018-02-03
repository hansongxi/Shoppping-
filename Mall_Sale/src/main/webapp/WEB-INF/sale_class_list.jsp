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
<title>商城</title>
<script type="text/javascript">
		$(function (){
			$.getJSON("js/json/class_1.js",function(data){
				$(data).each(function(i,json){
					$("#sale_list_class_1").append("<li onmouseover='class_list_get_class_2("+json.id+")' value="+json.id+"><a href=''>"+json.flmch1+"</a></li>");
				});
			});
		});
		
		function class_list_get_class_2(class_1_id){
			$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
				$("#sale_list_class_2").empty();
				$(data).each(function(i,json){
					$("#sale_list_class_2").append("<li value="+json.id+"><a target='_blank' href='goto_sale_sku_list.do?class_2_id="+json.id+"'>"+json.flmch2+"</a></li>");
				});
			});
		}
</script>
</head>
<body>

<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
					<div class="nav_mini">
						<ul id="sale_list_class_1">
							<li>
								<a href="">家用电器</a>
								<div class="two_nav" id="sale_list_class_2" style="colour:red"> 
								</div>
							</li>
							<li><a href="">手机、数码、通信</a></li>
							<li><a href="">电脑、办公</a></li>
							<li><a href="">家具、家居、家装</a></li>
						</ul>
					</div>
				</div>
				<ul>
					<li><a href="">服装城</a></li>
					<li><a href="">美妆馆</a></li>
					<li><a href="">超市</a></li>
					<li><a href="">全球购</a></li>
					<li><a href="">闪购</a></li>
					<li><a href="">团购</a></li>
					<li><a href="">拍卖</a></li>
					<li><a href="">金融</a></li>
					<li><a href="">智能</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="banner">
		<div class="ban">
			<img src="images/banner.jpg" width="980" height="380" alt="">
		</div>
	</div>
</body>
</html>