package com.zl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zl.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String Login(String login , String pass , HttpSession session){
		User user = new User();
		user.setLogin(login);

        //登录成功，先将用户存储到session中  
		session.setAttribute("user", user);
     
        //根据session中goURL是否有值而决定页面的跳转  
        if(session.getAttribute("goURL") == null) {  
            return "index"; //跳到首页  
        } else {  
        	String url = (String)session.getAttribute("goURL");
            return url;  
        } 
	}
	
	@RequestMapping("/tologin")
	public String ToLogin(HttpServletRequest request,HttpServletResponse response){
		return "user/ulogin";
	}
}
