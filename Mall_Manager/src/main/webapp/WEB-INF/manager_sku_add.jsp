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
		
		
		<div data-options="region:'north',border:false" style="height:50px;background:#B3DFDA;align-self: center;"><h2>商品库存单元管理页面</h2></div>
		<div data-options="region:'east',split:true,collapsed:true" style="width:100px;padding:10px;"></div>
		<div data-options="region:'south',border:false" style="height:0px;background:#A9FACD;"></div>
		<div data-options="region:'center'">
			<form action="save_sku.do" method="post">
				一级分类<select class="easyui-combobox" name="flbh1" id="sku_class_1" onchange="sku_get_class_2(this.value)"></select>
				二级分类<select class="easyui-combobox" name="flbh2" id="sku_class_2" onchange="get_attr_list(this.value)"></select>
				商品品牌<select class="easyui-combobox" name="pp_id" id="sku_tm" onchange="get_spu()"></select>
				商品信息<select class="easyui-combobox" name="shp_id" id="sku_spu" onchange="show_sku_form()"></select>
				<hr>
				<div id="attr_list_inner_html">
				</div>
				<hr/>
				<div id="sku_form_inner" style="display:none;">
					<jsp:include page="manager_sku_form.jsp"></jsp:include>
				</div>
				<input type="submit" value="提交"/>
			</form>
		</div>
	</div>


	
	<script type="text/javascript">
			$(function (){
				/* $.getJSON("js/json/class_1.js",function(data){
					$(data).each(function(i,json){
						$("#sku_class_1").append("<option value="+json.id+">"+json.flmch1+"</option>");
					});
					sku_get_class_2($("#sku_class_1")[0].value);
				}); */
				$('#sku_class_1').combobox({    
				    url:'js/json/class_1.js',    
				    valueField:'id',    
				    textField:'flmch1',
				    width:100,
				    onSelect:function(){
				    	var class_1_id=$(this).combobox("getValue");
				    	sku_get_class_2(class_1_id);
				    }
				});  
			});
			
			function sku_get_class_2(class_1_id){
				/* $("#sku_class_2").empty();
				$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
					$(data).each(function(i,json){
						$("#sku_class_2").append("<option value="+json.id+">"+json.flmch2+"</option>");
					});
				get_attr_list($("#sku_class_2")[0].value);
				});
				sku_tm_class(class_1_id); */
				$('#sku_class_2').combobox({    
				    url:"js/json/class_2_"+class_1_id+".js",    
				    valueField:'id',    
				    textField:'flmch2',
				    width:100,
				    onSelect:function(){
				    	var class_2_id=$(this).combobox("getValue");
				    	sku_tm_class(class_1_id,class_2_id);
				    }
				}); 
				
			}
			function sku_tm_class(class_1_id,class_2_id){
				/* $("#sku_tm").empty();
				$.getJSON("js/json/tm_class_1_"+class_1_id+".js",function(data){
					$(data).each(function(i,json){
						$("#sku_tm").append("<option value="+json.id+">"+json.ppmch+"</option>");
					});
				}); */
				$('#sku_tm').combobox({    
				    url:"js/json/tm_class_1_"+class_1_id+".js",    
				    valueField:'id',    
				    textField:'ppmch',
				    width:100,
				    onSelect:function(){
				    	var pp_id=$(this).combobox("getValue");
				    	get_attr_list(class_2_id,pp_id);
				    }
				}); 
				
			}
			function get_attr_list(class_2_id,pp_id){
					$.get("get_attr_list.do",{class_2_id:class_2_id},function(data){
						$("#attr_list_inner_html").html(data);
					get_spu(class_2_id,pp_id);
					});
			}
			function get_spu(class_2_id,pp_id){
				//var class_2_id=$("#sku_class_2").val();
				//var pp_id = $("#sku_tm").val();	
				/* $.get("get_spu_list.do",{class_2_id:class_2_id,pp_id:pp_id},function(data){
					$("#sku_spu").empty();
					$(data).each(function(i,json){
						$("#sku_spu").append("<option value="+json.id+">"+json.shp_mch+"</option>");
					})
					show_sku_form();
				}); */
				alert(pp_id);
				$('#sku_spu').combobox({    
				    
				    onBeforeLoad: function(param){
						param.class_2_id = class_2_id;
						param.pp_id = pp_id;
					},
					url:"get_spu_list.do", 
				    valueField:'id',    
				    textField:'shp_mch',
				    width:100,
				    onSelect:function(){
				    	
				    	show_sku_form();
				    }
				}); 
				
			}
			function show_sku_form(){
				$("#sku_form_inner").show();
			}
	</script>
</body>
</html>