package com.zl.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.entity.User;
import com.zl.service.UserService;

/**
 * 主页Controller
 * @author zhouliang
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	@Resource
	private UserService userService;
	/**
	 * @Description 请求主页
	 * @return
	 */
	@RequestMapping("/index")
	public void index(HttpServletRequest request,HttpServletResponse response){
		try {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description 请求后台管理主页
	 * @return
	 */
	@RequestMapping("/aindex")
	public String aindex() throws Exception {
		return "main/aindex";
	}
}
