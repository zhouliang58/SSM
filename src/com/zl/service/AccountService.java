package com.zl.service;

import java.util.List;

import com.zl.entity.Account;

/**
 * 后台商品管理员
 * @author zhouliang
 *
 */
public interface AccountService {
	//获取所有的管理员信息
	public List<Account> getAllAccount();
}
