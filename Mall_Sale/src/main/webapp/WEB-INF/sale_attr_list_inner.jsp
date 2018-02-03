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
	function get_param_up(shxm_id,shxzh_id,shxzhmch){
		//数组的形式传值
		//$("#attr_param").append("<input name='attr_param' type='text' "
				//+"value='"+shxm_id+"_"+shxzh_id+"'/>"+" "+shxzhmch);
		//json的传输
		$("#attr_param").append("<input name='attr_param' type='text' value='{\"shxm_id\":"+shxm_id+",\"shxzh_id\":"+shxzh_id+"}'/>"+" "+shxzhmch);
		get_sku_list_json();
		}
	//json的传输
	function get_sku_list_json() {
		// 二级分类id
		var class_2_id = "${class_2_id}";
		
		// 参数，jquery数组
		var inputs = $("#attr_param input[name='attr_param']");

		// 声明一个查询字符串
		var param = "class_2_id="+class_2_id;
		
		// 将jquery数组转化为js的数组对象
		$(inputs).each(function(i,data){			
			var json = jQuery.parseJSON(data.value);
			param= param +"&list_sku_attr_value["+i+"].shxm_id="+json.shxm_id+"&list_sku_attr_value["+i+"].shxzh_id="+json.shxzh_id;
		});
		
 		$.get("get_sku_list_by_attr.do",param, function(html) {
			$("#sku_list_inner").html(html);
		}); 
	}
	//数组的传输
	function get_sku_list_array(){
		var class_2_id = "${class_2_id}";
		//获取div中的input标签的中name的属性值 input是个jquery数组
		var inputs=  $("#attr_param input[name='attr_param']");
		
		//定义一个js数组对象
		var attr_param = new Array();
		// 将jquery数组转化为js的数组对象
		$(inputs).each(function(i,data){
			attr_param[i] = data.value;
			
		});
		//在运用ajax传递的时候，请求字符串；会把js对象转化为请求字符串
		//转化为查询字符以配合application/x-WWW-form-urlencoding格式
		$.getJSON("get_sku_list_by_attr.do",
			
			{
				class_2_id: class_2_id,
				attr_param: attr_param
			},function(data){
			$("#sku_list_inner").html(data);
			});
	}
	
	
	
</script>
</head>
<body>
	<h2>属性列表</h2>12
	<div id="attr_param" style="display: none;">
		属性值区域
	</div>
	<c:forEach items="${list_attr}" var="attr" varStatus="status">
		${attr.shxm_mch}:
		<c:forEach items="${attr.list_value}" var="val">
			<a 
			href="javascript:get_param_up(${attr.id},${val.id},'${val.shxzh}${val.shxzh_mch}');">
				${val.shxzh}${val.shxzh_mch}
			</a>
		</c:forEach>
		<br>
	</c:forEach>
</body>
</html>