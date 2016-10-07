package com.zl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zl.entity.Account;
import com.zl.entity.AccountExample;
import com.zl.mapper.AccountMapper;
import com.zl.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService{

	@Resource
	private AccountMapper accountMapper;
	
	@Override
	public List<Account> getAllAccount() {
		AccountExample accountexample = new AccountExample();	
		return accountMapper.selectByExample(accountexample);
	}

}
