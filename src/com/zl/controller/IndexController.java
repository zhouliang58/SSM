package com.zl.controller;

import javax.annotation.Resource;

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
	public String index(Model model) throws Exception {
		User user = userService.getUserById(1);
		model.addAttribute("user", user);
		return "index";
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
