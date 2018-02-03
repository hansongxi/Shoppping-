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

	<div class="easyui-layout" data-options="fit: true">
		<div data-options="region:'north',border:false" style="height:50px;background:#B3DFDA;align-self: center;"><h2>商品信息添加页面</h2></div>
		<div data-options="region:'east',split:true,collapsed:true" style="width:100px;padding:10px;"></div>
		<div data-options="region:'south',border:false" style="height:0px;background:#A9FACD;"></div>
		<div data-options="region:'center'">
			<form action="save_spu.do" method="post" enctype="multipart/form-data">
				一级分类<select  class="easyui-combobox" name="flbh1" id="spu_class_1" onchange="spu_get_class_2(this.value)"></select>
				二级分类<select class="easyui-combobox" name="flbh2" id="spu_class_2" "></select>
				商品品牌<select class="easyui-combobox" name="pp_id" id="tm_class"></select>
				<hr>
				商品名称:<input name="shp_mch" type="text" value="商品信息描述"><br/><br/><br/>
				商品描述:<textarea name="shp_msh"></textarea><br/>
				商品图片:
				<div id="div_id">
					<img id="img_id_0" src="image/upload_hover.png" width="100px" height="50px" onclick="button_img_click(0)">
					<input id="file_id_0" name="files" type="file" onchange="button_img_change(0)" style="display: none"><br/>
				</div>
				<input type="submit" value="提交"/>
			</form>
		</div>
	</div>

	
	<script type="text/javascript">
		$(function (){
			/* $.getJSON("js/json/class_1.js",function(data){
				$(data).each(function(i,json){
					$("#spu_class_1").append("<option value="+json.id+">"+json.flmch1+"</option>");
				});
			
				spu_get_class_2($("#spu_class_1")[0].value);
			}); */
			//spu_get_class_2($("#spu_class_1").children("option")[0].value);
			$('#spu_class_1').combobox({    
			    url:'js/json/class_1.js',    
			    valueField:'id',    
			    textField:'flmch1',
			    width:100,
			    onSelect:function(){
			    	var class_1_id=$(this).combobox("getValue");
			    	spu_get_class_2(class_1_id);
			    }
			});  
		
		});
		
		function spu_get_class_2(class_1_id){
			/* $("#spu_class_2").empty();
			$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
				$(data).each(function(i,json){
					
					$("#spu_class_2").append("<option value="+json.id+">"+json.flmch2+"</option>");
				});
			
			});
			spu_tm_class(class_1_id); */
			$('#spu_class_2').combobox({    
			    url:"js/json/class_2_"+class_1_id+".js",    
			    valueField:'id',    
			    textField:'flmch2',
			    width:100,
			    onSelect:function(){
			    	spu_tm_class(class_1_id);
			    }
			});
		}
		function spu_tm_class(class_1_id){
			/* $("#tm_class").empty();
			$.getJSON("js/json/tm_class_1_"+class_1_id+".js",function(data){
				$(data).each(function(i,json){
					$("#tm_class").append("<option value="+json.id+">"+json.ppmch+"</option>");
				});
			}); */
			$('#tm_class').combobox({    
			    url:"js/json/tm_class_1_"+class_1_id+".js",    
			    valueField:'id',    
			    textField:'ppmch',
			    width:100
			});
			
		}
		function button_img_click(index){
			$("#file_id_"+index).click();
		}
		function button_img_change(index){
			/*获取浏览器的缓存  */
			var file = $("#file_id_"+index)[0].files[0];
		
			var url = window.URL.createObjectURL(file);
			
			$("#img_id_"+index).attr("src",url);
			
			/*判断是否是选中最后一个  */
			var length = $("#div_id :file").length;
			if((index+1)==length){
				button_img_append(index);	
			}
		}
		function button_img_append(index){
			
			var a='<img id="img_id_'+(index+1)+'" src="image/upload_hover.png" width="100px" height="50px" onclick="button_img_click('+(index+1)+')"/>';
			var b='<input id="file_id_'+(index+1)+'" name="files" type="file" onchange="button_img_change('+(index+1)+')" style="display: none"/><br/>';
			$("#div_id").append(a+b);
		}
	
</script>
</body>
</html>