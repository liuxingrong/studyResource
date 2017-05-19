package com.learning.drp.service.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.learning.drp.domain.User;
import com.learning.drp.service.UserService;

public class UserServiceImpl extends HibernateDaoSupport implements UserService{

	@Override
	public User validateUser(String username, String password) {
		String hql = "from User where username='" + username + "'and password='" + password +"'and isActive = 1";
        Query query = this.getSession().createQuery(hql);
        List<User> list = (List<User>) query.list();
        if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean isRegister(String username) {
		String hql = "from User where username='" + username + "' and isActive = 1";
        Query query = this.getSession().createQuery(hql);
        User user = new User();
        List<User> list = (List<User>) query.list();
        if (list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void Register(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setType(1);
		user.setIsActive(1);
		user.setCreateTime(new Date(System.currentTimeMillis()));
		this.getSession().save(user);
		
	}


}
