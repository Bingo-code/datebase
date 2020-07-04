package com.xrb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xrb.entity.Audio;
import com.xrb.entity.Image;
import com.xrb.service.UserService;

@Controller
@RequestMapping("/mainC")
public class MainController {
	@Resource
	private UserService userService;
	
	
	@RequestMapping("/showImageByPage")
	public ModelAndView showImageByPage(Integer showPageNum, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		String  userName = (String)session.getAttribute("userName");
		
		ModelAndView mav = new ModelAndView();
		List<Audio> audioList = userService.findAudiosIsShow(userId);
		mav.addObject("audioShow", audioList);
		int allImagesNum = userService.findImagesIsShow(userId).size();
		int pageNum = (int) Math.ceil(allImagesNum/18.0);
		List<Image> imageList = userService.queryImages_18(new RowBounds((showPageNum-1)*18, 18), userId);
		mav.addObject("imageShow", imageList);
		mav.addObject("pageNum",pageNum);
		mav.addObject("userName", userName);
		mav.addObject("showPageNum", showPageNum);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/initShow")
	public ModelAndView initShowImage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		String  userName = (String)session.getAttribute("userName");
		
		ModelAndView mav = new ModelAndView();
		List<Audio> audioList = userService.findAudiosIsShow(userId);
		mav.addObject("audioShow", audioList);
		int allImagesNum = userService.findImagesIsShow(userId).size();
		int pageNum = (int) Math.ceil(allImagesNum/18.0);
		List<Image> imageList = userService.queryImages_18(new RowBounds(0, 18), userId);
		mav.addObject("imageShow", imageList);
		mav.addObject("pageNum",pageNum);
		mav.addObject("userName", userName);
		mav.addObject("showPageNum", 1);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("ajaxFindImageByPage")
	@ResponseBody
	public List<Image> ajaxFindImageByPage(Integer showPageNum, HttpServletRequest request){
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		List<Image> list = userService.queryImages_18(new RowBounds((showPageNum-1)*18, 18), userId);
		
		return list;
	}
	
	@RequestMapping("/quit")
	public String quit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/loginRegister.html";
	}
}
