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

<title>硅谷商城</title>
</head>
<body>
	<div  class="easyui-layout" data-options="fit: true">
		<div data-options="region:'north',border:false" style="height:50px;background:#B3DFDA;align-self: center;">
				<h2>商品属性管理页面</h2>
		</div>
		<div data-options="region:'east',split:true,collapsed:true" style="width:100px;padding:10px;"></div>
		<div data-options="region:'south',border:false" style="height:0px;background:#A9FACD;"></div>
			<div data-options="region:'center'">
				<form action="save_attr.do" method="post"> 
					<jsp:include page="manager_class_attr.jsp"></jsp:include>
					<hr/>
					<table border="1" id="add_value_table"> 
						<tr>
							<td>
							属性名：<input type="text" name="list_attr[0].shxm_mch"/>
							</td>
							<td align="center">
								
							</td>
							<td  id="value_id" onclick="add_value()">
								添加值
							</td>
						</tr>
						<tr id="value_tr_0">
							<td>
							属性值：<input type="text" name="list_attr[0].list_value[0].shxzh"/>
							</td>
							<td>
							单位：<input type="text" name="list_attr[0].list_value[0].shxzh_mch"/>
							</td>
							<td id="delete_value_0" onclick="delete_value(0)">
								删除
							</td>
							
						</tr>
					
					</table>
					<input type="submit" value="提交"/>
				</form>
			
			</div>
	</div> 
	<script type="text/javascript">
		$(function (){});
		function add_value(){
			var index=$("#add_value_table tr").length-1;
			//alert(index);
			var a='<tr id="value_tr_'+index+'"><td>属性值：<input type="text" name="list_attr[0].list_value['+index+'].shxzh"/></td>';
			var b='<td> 单位：<input type="text" name="list_attr[0].list_value['+index+'].shxzh_mch"/></td>'
			var c='<td id="delete_value_'+index+'" onclick="delete_value('+index+')"> 删除 </td> ';
			$("#add_value_table").append(a+b+c);
			
		}
		function delete_value(index){
			$("#value_tr_"+index).empty();
			$("#value_tr_"+index).attr("style","display:none");
			
		}
	
	</script>
</body>
</html>