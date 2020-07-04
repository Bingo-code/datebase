package com.xrb.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xrb.entity.Audio;
import com.xrb.entity.Image;
import com.xrb.entity.User;
import com.xrb.service.UserService;

@RestController
@RequestMapping("/lr")
public class LoginRegisterController {
//	private static Log logger = LogFactory.getLog(LoginRegisterController.class);
//	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	
	@Resource
	private UserService userService;
	

	@RequestMapping("/login")
	public ModelAndView login(User user, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("info", "lInfo");
		User sqlUser = userService.queryUserByName(user.getUserName());
		String loginInfo = "登录成功";
		if(sqlUser!=null) {
			//登陆成功则初始化主页面
			if(sqlUser.getUserPwd().equals(user.getUserPwd())) {
				mav.addObject("tips", loginInfo);
				mav.addObject("userName", user.getUserName());
				HttpSession session = request.getSession();
				if(session.getAttribute("user")==null) {
					session.setAttribute("userId", sqlUser.getUserId());
					session.setAttribute("userName", sqlUser.getUserName());
					session.setMaxInactiveInterval(10*60);
				}
//				logger.info("\n"+format.format(new Date())+"\n用户登录，验证用户信息---"+loginInfo);
				List<Audio> audioList = userService.findAudiosIsShow(sqlUser.getUserId());
				mav.addObject("audioShow", audioList);
				int allImagesNum = userService.findImagesIsShow(sqlUser.getUserId()).size();
				int pageNum = (int) Math.ceil(allImagesNum/18.0);
				List<Image> imageList = userService.queryImages_18(new RowBounds(0, 18), sqlUser.getUserId());
				mav.addObject("imageShow", imageList);
				mav.addObject("pageNum",pageNum);
				mav.addObject("showPageNum",1);
				mav.setViewName("main");
				return mav;
			}else {
				loginInfo = "密码不正确";
			}
			
		}else{
			loginInfo = "账号不存在";
			
		}
		mav.addObject("lInfo", loginInfo);
		mav.addObject("lUserName", user.getUserName());
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping("/register")
	public ModelAndView ajaxRegister(User user) throws IOException {
		ModelAndView mav = new ModelAndView();
		mav.addObject("info", "rInfo");

		User sqlUser = userService.queryUserByName(user.getUserName());
		String registerInfo = "注册成功";
		if(sqlUser == null) {
			user.setUserId(UUID.randomUUID()+"");
			int num = userService.addUser(user);
			
			if(num <= 0) {
				registerInfo = "注册失败";
			}
			
		}else {
			registerInfo = "账号已存在";
		}
		mav.addObject("rInfo", registerInfo);
		mav.addObject("rUserName", user.getUserName());
		mav.setViewName("login");
		return mav;

	}
	
	
	
	
}
