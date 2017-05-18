package com.learning.drp.service;

import com.learning.drp.domain.User;

public interface UserService {
	
	public User validateUser(String username, String password);
	
	public boolean isRegister(String username);
	
	public void register(String username, String password);

}
