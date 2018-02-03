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
<script type="text/javascript">
		$(function (){
			/* $.getJSON("js/json/class_1.js",function(data){
				$(data).each(function(i,json){
					$("#attr_class_1").append("<option value="+json.id+">"+json.flmch1+"</option>");
				});
				arrt_get_class_2($("#attr_class_1")[0].value);
			}); */
			$('#attr_class_1').combobox({    
			    url:'js/json/class_1.js',    
			    valueField:'id',    
			    textField:'flmch1',
			    width:100,
			    onSelect:function(){
			    	var class_1_id=$(this).combobox("getValue");
			    	attr_get_class_2(class_1_id);
			    }
			});  
		});
		
		function attr_get_class_2(class_1_id){
			/* $("#attr_class_2").empty();
			$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
				$(data).each(function(i,json){
					$("#attr_class_2").append("<option value="+json.id+">"+json.flmch2+"</option>");
				});
			}); */
			$('#attr_class_2').combobox({    
			    url:"js/json/class_2_"+class_1_id+".js",    
			    valueField:'id',    
			    textField:'flmch2',
			    width:100
			   
			});
			
		}
		
		
</script>
<title>硅谷商城</title>
</head>
<body>
	
		一级分类<select class="easyui-combobox" name="flbh1" id="attr_class_1" onchange="attr_get_class_2(this.value)"></select>
		二级分类<select class="easyui-combobox" name="flbh2" id="attr_class_2"></select>
</body>
</html>