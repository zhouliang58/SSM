package com.zl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zl.entity.User;
import com.zl.mapper.UserMapper;
import com.zl.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;
	@Override
	public User getUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

}
