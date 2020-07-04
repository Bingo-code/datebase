package com.xrb.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xrb.entity.Image;
import com.xrb.io.FileManage;
import com.xrb.service.UserService;
import com.xrb.util.FormatDate;
import com.xrb.util.FormatSize;

@Controller
@RequestMapping("/imageC")
public class ImageController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/initImageManage")
	public ModelAndView initImageManage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String  userId = (String)session.getAttribute("userId");
		String  userName = (String)session.getAttribute("userName");
		ModelAndView mav = new ModelAndView();
		List<Image> imageIsValid = userService.findImagesIsValid(userId);
		mav.addObject("userName", userName);
		mav.addObject("imageIsValid", imageIsValid);
		mav.setViewName("imageManage");
		return mav;
	}
	
	@RequestMapping("/addFile")
	@ResponseBody
	public List<String> addFile(MultipartFile[] uploadFiles,boolean replace, HttpServletRequest request) throws Exception {
		ArrayList<String> list = new ArrayList<>();
		String userId = (String)request.getSession().getAttribute("userId");
		
		for (MultipartFile file : uploadFiles) {
			
			String imageId = UUID.randomUUID()+"";
			String imageName = file.getOriginalFilename();
			String imageType = file.getContentType();
			System.out.println(imageType);
			Pattern pattern = Pattern.compile("image(/jpg|/JPG|/png|/PNG|/jpeg|/JPEG)$");
			Matcher matcher = pattern.matcher(imageType);
			long fileSize = file.getSize();
			if(fileSize>10*1024*1024) {
				list.add(imageName+" 单个文件大小超限");
				continue;
			}
			String imageSize = FormatSize.size(fileSize);
			String imageTime = FormatDate.date(new Date());
			String writePath =  "src/main/resources/static/image/"+imageName;
			
			Integer addFile = null;
			if(matcher.matches()) {
				addFile = FileManage.addFile(file, writePath, replace);
				System.out.println(addFile);
			}else {
				list.add(imageName+" 文件类型不匹配");
				continue;
			}
			
			if(addFile == 1) {
				Integer addDataBase = userService.addImage(new Image(imageId, imageName, imageType, imageSize, imageTime, userId));
				if(addDataBase<1) {
					FileManage.delFile(writePath);
					list.add(imageName+" 上传失败");
				}else {
					list.add(imageName+" 上传成功");
				}
			}else if(addFile == 2){
				Integer addDataBase = userService.updateImage(new Image(imageId, imageName, imageType, imageSize, imageTime, userId));
				
				if(addDataBase<1) {
					FileManage.delFile(imageName);
					list.add(imageName+" 替换失败");
				}else {
					list.add(imageName+" 替换成功");
				}
			}else if(addFile == 0) {
				list.add(imageName + " 文件已存在");
			}else {
				list.add(imageName + " 上传异常");
			}
		}
		
		return list;
	}
	
	@RequestMapping("/deleteImage")
	public String deleteimage(String imageId) {
		String imageName = userService.findImageById(imageId).getImageName();
		String delFilePath = "src/main/resources/static/image/"+imageName;
		FileManage.delFile(delFilePath);
		userService.deleteImage(imageId);
		return "forward:/imageC/initImageManage";
	}
	
	@RequestMapping("/renameImage")
	@ResponseBody
	public String rename(Image image,String oldImageName) {
		String newFilePath = "src/main/resources/static/image/"+image.getImageName();
		String oldFilePath = "src/main/resources/static/image/"+oldImageName;
		FileManage.renameFile(oldFilePath, newFilePath);
		userService.renameImage(image);
		
		return "重命名成功";
	}
	
	@RequestMapping("/reImageShow")
	public String reimageShow(Image image) {
		userService.reImageShow(image);
		return "forward:/imageC/initImageManage";
	}
	
	
	
}
