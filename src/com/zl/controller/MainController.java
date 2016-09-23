package com.zl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 后台Controller
 * @author zhouliang
 *
 */
@Controller
@RequestMapping("/main")
public class MainController {
	
	/**
	 * @Description 请求主页
	 * @return
	 */
	@RequestMapping("/aindex")
	public String index() throws Exception {
		return "main/aindex";
	}
}
