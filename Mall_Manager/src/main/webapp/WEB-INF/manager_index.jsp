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
<link rel="stylesheet" type="text/css" href="js/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function(){
		var url = "${url}";
		var title ="${title}";
		if(url!=null){
			add_tab(url,title);
		}
		
	}); 
	function add_tab(url,title){
		var b = $('#list_display').tabs('exists',title);
		if(b){
			$('#list_display').tabs('select',title);
		} else{
			$("#list_display").tabs('add',{ 
				
			    title:title,
			    href:url,
			    closable:true,    
			    tools:[{    
			        iconCls:'icon-mini-refresh',    
			        handler:function(){    
			            alert('refresh');    
			        }    
			    }]    
			});  
		}
	}
	
</script>
<title>硅谷商城</title>
</head>
<body class="easyui-layout">
		
		
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
		<h2>后台管理首页</h2>
	</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:200px;padding:10px;">
		<div class="easyui-accordion" style="width:180px;height:300px;">
			<div title="商品管理"  data-options="iconCls:'icon-search'" style="padding:10px;">
				<ul class="easyui-tree">
					<li>
						<span>商品管理</span>
						<ul>
							<li>
								<span>商品信息管理</span>
								<ul>
									<li><a href="goto_spu_add.do">商品信息查询 </a></li>
									<li><a href="javascript:add_tab('goto_spu_add.do','商品信息添加');">商品信息添加</a></li>
								</ul>
							 </li>
							<li>
								<span>商品属性管理 </span>
								<ul>
									<li><a href="javascript:add_tab('goto_attr_list.do','商品属性查询');">商品属性查询 </a></li>
									<li><a href="javascript:add_tab('goto_attr_add.do','商品属性添加');">商品属性添加</a></li>
								</ul>
							</li>
							<li>
								<span>商品库存单元管理</span>
								<ul>
									<li><a href="goto_sku_add.do">商品库存单元查询 </a></li>
									<li><a href="javascript:add_tab('goto_sku_add.do','商品库存单元添加');">商品库存单元添加</a></li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<div title="缓存管理"  data-options="iconCls:'icon-search'" style="padding:10px;">
				<a href="javascript:add_tab('goto_cache.do','商品属性缓存');">商品信息的缓存管理</a>
			</div>
		</div>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="background:#A9FACD;padding:10px;" align="center">
			hanhan@123.com
	</div>
	<div data-options="region:'center',title:'Center'">
		 <div id="list_display" class="easyui-tabs" style="height:500px;" >
			
		</div> 
		<!-- 
		<div  class="easyui-layout" data-options="fit: true">
			<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north</div>
			<div data-options="region:'west',split:true,title:'West'" style="width:200px;padding:10px;">west</div>
			<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
			<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
			<div data-options="region:'center',title:'Center'"></div>
		</div>
		--> 
	</div>
		
		
		
		
	
</body>
</html>
