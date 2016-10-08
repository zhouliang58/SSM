package com.zl.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zl.entity.User;
import com.zl.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String Login(String login , String pass , HttpSession session){
		
		User user = userService.getUserByLogin(login);
		if(user!=null&&user.getPass().equals(pass)){
	        //登录成功，先将用户存储到session中  
			user.setPass(null);
			session.setAttribute("user", user);
	     
	        //根据session中goURL是否有值而决定页面的跳转  
	        if(session.getAttribute("goURL") == null) {  
	            return "index"; //跳到首页  
	        } else {  
	        	String url = "redirect:"+(String)session.getAttribute("goURL");
	            return url;  
	        } 
		}else{
	        //登陆失败，跳转到登陆页面
			session.setAttribute("error", "用户名或者密码错误，请重新登录！");  
			return "forward:tologin.html";
		}

	}
	
	@RequestMapping("/tologin")
	public void ToLogin(HttpServletRequest request,HttpServletResponse response){
		try {
			request.getRequestDispatcher("/ulogin.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/toconfirm")
	public String ToConfirm(){
		return "user/confirm";
	}
}
