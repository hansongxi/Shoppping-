package com.hanhan.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class MyUploadUtil {

	public static List<String> upload_img(MultipartFile[] files) {
		List<String> list_image = new ArrayList<String>();

		// 设置一个读取properties的工具
		String path = MyPropertyUtil.getMyProperty("upload.properties", "windows_path");
		for (int i = 0; i < files.length; i++) {

			if (!files[i].isEmpty()) {

				// 在另存为图片的时候需要路径和文件名
				// 获取文件名,因为会有重复，所以是UUID或者当前的毫秒数
				UUID uuid = UUID.randomUUID();
				String file_name = uuid + files[i].getOriginalFilename();

				try {
					files[i].transferTo(new File(path + File.separator + file_name));
					list_image.add(file_name);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list_image;
	}

}
