package com.xrb.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyInterceptorConfig implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String userName = (String)request.getSession().getAttribute("userName");
		if(userName==null) {
			response.sendRedirect("/td_space/loginRegister.html");
			return false;
		}
		return true;
	}
}
