package com.hanhan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hanhan.bean.T_MALL_PRODUCT;
import com.hanhan.service.SpuService;
import com.hanhan.utils.MyUploadUtil;

@Controller
public class SpuController {
	
	@Autowired
	private SpuService spuService;

	@RequestMapping("goto_spu")
	public String goto_spu() {
		return "manager_spu";
	}
	
	@RequestMapping("goto_spu_add")
	public String goto_spu_add() {
		return "manager_spu_add";
	}

	@RequestMapping("save_spu")
	public ModelAndView save_spu(T_MALL_PRODUCT spu,@RequestParam("files") MultipartFile[] files) {
		/*浏览器传入进来的是product的值和图片的值
		1.向数据库中插入product数据
		2.product_image中插入数据
		两个都属于product，所以只需要用一个插入即可，但是在插入图片的时候需要图片的的名字*/
		//1.上传照片；需要一个方法去上传照片
		List<String> list_image = MyUploadUtil.upload_img(files);
		ModelAndView mv = new ModelAndView("redirect:/index.do");
		//2.向数据库中插入数据
		spuService.save_spu(spu,list_image);
		mv.addObject("url", "goto_spu_add.do");
		mv.addObject("title", "商品信息添加");
		Gson gson = new Gson();
		String gsonStr=gson.toJson(mv);
		//System.out.println(gsonStr);
		return mv;
	}
	
	
	/*@RequestMapping("save_spu")
	public String save_spu() {
		return "redirect:/goto_spu_add.do";
	}*/
}
