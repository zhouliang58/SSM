package com.zl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zl.entity.Account;
import com.zl.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml")  
public class AccountServiceImplTest {  
      
	@Resource
	private AccountService accountService;
      
    @Test 
    public void getAllAccount() {  
    	List<Account> result = accountService.getAllAccount();
    	System.out.println(result.size());
    }  
    
}  