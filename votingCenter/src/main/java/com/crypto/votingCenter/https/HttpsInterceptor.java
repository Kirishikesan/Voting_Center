package com.crypto.votingCenter.https;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HttpsInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
	    System.out.println("Pre Handle method is Calling");
	    System.out.println(request.getServletContext());
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler, ModelAndView modelAndView) throws Exception{
	      System.out.println("Post Handle method is Calling");
	}
	
}
