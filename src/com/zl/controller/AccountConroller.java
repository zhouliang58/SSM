package com.zl.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zl.entity.Account;
import com.zl.service.AccountService;
import com.zl.util.JsonUtils;

@Controller
@RequestMapping("/account")
public class AccountConroller {
	
	@Resource
	private AccountService accountService;
	
	@RequestMapping(value="/query", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String query()throws Exception {
		List<Account> accounts = accountService.getAllAccount();
		return JsonUtils.objectToJson(accounts);
	}
}
