package com.zl.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zl.entity.User;
import com.zl.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml")  
public class UserServiceImplTest {  
      
	@Resource
	private UserMapper userMapper;
      
    @Test 
    public void getUserById() {  
    	User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.getName());  
    }  
}  