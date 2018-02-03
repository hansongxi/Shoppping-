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
<title>Insert title here</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit: true">
			<div data-options="region:'north',border:false" style="height:50px;background:#B3DFDA;align-self: center;">
				一级分类<select class="easyui-combobox" name="flbh1" id="attr_list_class_1" onchange="attr_list_get_class_2(this.value)"></select>
				二级分类<select class="easyui-combobox" name="flbh2" id="attr_list_class_2" onchange="get_attr_list(this.value)"></select>
			</div>
			<div data-options="region:'west',split:true,title:'West'" style="width:100px;padding:10px;"></div>
			<div data-options="region:'east',split:true,collapsed:true" style="width:100px;padding:10px;"></div>
			<div data-options="region:'south',border:false" style="height:0px;background:#A9FACD;"></div>
			<div data-options="region:'center'">
				<div id="get_attr_list_inner_html">
				
				</div>
			</div>
	</div>
		<script type="text/javascript">
			$(function (){
				/* $.getJSON("js/json/class_1.js",function(data){
					$(data).each(function(i,json){
						$("#attr_list_class_1").append("<option value="+json.id+">"+json.flmch1+"</option>");
					});
					attr_list_get_class_2($("#attr_list_class_1")[0].value);
				}); */
				
				$('#attr_list_class_1').combobox({    
				    url:'js/json/class_1.js',    
				    valueField:'id',    
				    textField:'flmch1',
				    width:100,
				    onSelect:function(){
				    	var class_1_id=$(this).combobox("getValue");
				    	attr_list_get_class_2(class_1_id);
				    }
				});  
			});
			
			function attr_list_get_class_2(class_1_id){
				/* $("#attr_list_class_2").empty();
				$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
					$(data).each(function(i,json){
						$("#attr_list_class_2").append("<option value="+json.id+">"+json.flmch2+"</option>");
					});
				//get_attr_list($("#attr_list_class_2")[0].value);
				}); */
				$('#attr_list_class_2').combobox({ 
				    url:"js/json/class_2_"+class_1_id+".js",    
				    valueField:'id',    
				    textField:'flmch2',
				    width:100,
				    onSelect:function(){
				    	var class_2_id=$(this).combobox("getValue");
				    	get_attr_list(class_2_id);
				    }
				});
				
			}
			
			function get_attr_list(class_2_id){
					/* $.get("get_attr_list_inner.do",{class_2_id:class_2_id},function(data){
						$("#get_attr_list_inner_html").html(data);
					get_spu();
					}); */
				$('#get_attr_list_inner_html').datagrid({    
				    url:'get_attr_list_inner_json.do', 
				    queryParams: {
				    	class_2_id: class_2_id
					},
				    columns:[[    
				        {field:'id',title:'属性ID',width:100},    
				        {field:'shxm_mch',title:'属性名',width:100},    
				        {field:'list_value',title:'属性值',width:300,align:'center',
				        	formatter: function(value,row,index){
								var str="";
								$(value).each(function(i,json){
									str = str+" "+json.shxzh+json.shxzh_mch;
								});
								return str;
							}

				        
				        }, 
				        {field:'chjshj',title:'创建时间',width:150,align:'center',
				        	formatter: function(value,row,index){
								
								return new Date(value).toLocaleString();
							}	
				        
				        }
				    ]]    
				});  
			}
			
			
	</script>
</body>
</html>