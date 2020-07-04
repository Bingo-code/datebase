package com.xrb.controller;


import java.io.File;
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

import com.xrb.entity.Audio;
import com.xrb.io.FileManage;
import com.xrb.service.UserService;
import com.xrb.util.FormatDate;
import com.xrb.util.FormatSize;

@Controller
@RequestMapping("/audioC")
public class AudioController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/initAudioManage")
	public ModelAndView initAudioManage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String  userId = (String)session.getAttribute("userId");
		String  userName = (String)session.getAttribute("userName");
		ModelAndView mav = new ModelAndView();
		List<Audio> audioIsValid = userService.findAudiosIsValid(userId);
		mav.addObject("userName", userName);
		mav.addObject("audioIsValid", audioIsValid);
		mav.setViewName("audioManage");
		return mav;
	}
	
	@RequestMapping("/addFile")
	@ResponseBody
	public List<String> addFile(MultipartFile[] uploadFiles, boolean replace,HttpServletRequest request) throws Exception {
		ArrayList<String> list = new ArrayList<>();
		String userId = (String)request.getSession().getAttribute("userId");
		System.out.println(uploadFiles.length);
		for (MultipartFile file : uploadFiles) {
			
			String audioId = UUID.randomUUID()+"";
			String audioName = file.getOriginalFilename();
			String audioType = file.getContentType();

			Pattern pattern = Pattern.compile("audio(/mp3|/MP3|/flac|/FLAC)$");
			Matcher matcher = pattern.matcher(audioType);
			long fileSize = file.getSize();
			if(fileSize>10*1024*1024) {
				list.add(audioName+" 单个文件大小超限");
				continue;
			}
			String audioSize = FormatSize.size(fileSize);
			String audioTime = FormatDate.date(new Date());
			String writePath =  "src/main/resources/static/audio/"+audioName;
			Integer addFile = null;
			if(matcher.matches()) {
				addFile = FileManage.addFile(file, writePath, replace);
				System.out.println(addFile);
			}else {
				list.add(audioName+" 文件类型不匹配");
				continue;
			}
			
			if(addFile == 1) {
				Integer addDataBase = userService.addAudio(new Audio(audioId, audioName, audioType, audioSize, audioTime, userId));
				if(addDataBase<1) {
					FileManage.delFile(writePath);
					list.add(audioName+" 上传失败");
				}else {
					list.add(audioName+" 上传成功");
				}
			}else if(addFile == 2){
				Integer addDataBase = userService.updateAudio(new Audio(audioId, audioName, audioType, audioSize, audioTime, userId));
				
				if(addDataBase<1) {
					FileManage.delFile(audioName);
					list.add(audioName+" 替换失败");
				}else {
					list.add(audioName+" 替换成功");
				}
			}else if(addFile == 0) {
				list.add(audioName + " 文件已存在");
			}else {
				list.add(audioName + " 上传异常");
			}
		}
		
		return list;
	}
	
	@RequestMapping("/deleteAudio")
	public String deleteAudio(String audioId) {
		String audioName = userService.findAudioById(audioId).getAudioName();
		String delFilePath = "src/main/resources/static/audio/"+audioName;
		FileManage.delFile(delFilePath);
		userService.deleteAudio(audioId);
		return "forward:/audioC/initAudioManage";
	}
	
	@RequestMapping("/renameAudio")
	@ResponseBody
	public String rename(Audio audio,String oldAudioName) {
		String newFilePath = "src/main/resources/static/audio/"+audio.getAudioName();
		String oldFilePath = "src/main/resources/static/audio/"+oldAudioName;
		File file = new File(newFilePath);
		if(!file.exists()&&!file.isDirectory()) {
			FileManage.renameFile(oldFilePath, newFilePath);
			userService.renameAudio(audio);
			return "重命名成功";
		}else {
			return "文件名已被占用";
		}
		
	}
	
	@RequestMapping("/reAudioShow")
	public String reAudioShow(Audio audio) {
		userService.reAudioShow(audio);
		return "forward:/audioC/initAudioManage";
	}
}
