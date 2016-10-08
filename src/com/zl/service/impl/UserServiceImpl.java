package com.zl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zl.entity.User;
import com.zl.entity.UserExample;
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
	@Override
	public User getUserByLogin(String login) {
		UserExample userExample = new UserExample();
		com.zl.entity.UserExample.Criteria criteria  = userExample.createCriteria();
		criteria.andLoginEqualTo(login);
		List<User> users =  userMapper.selectByExample(userExample);
		if(users.size()>0){
			return users.get(0);
		}else{
			return null;
		}

	}

}
