package com.zl.service;

import com.zl.entity.User;

public interface UserService {
	public User getUserById(Integer id);
	
	public User getUserByLogin(String login);
}
