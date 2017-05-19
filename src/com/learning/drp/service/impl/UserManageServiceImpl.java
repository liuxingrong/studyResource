package com.learning.drp.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.learning.drp.domain.User;
import com.learning.drp.service.UserManageService;

public class UserManageServiceImpl extends HibernateDaoSupport implements UserManageService{

	@Override
	public void add(User user) {
	   user.setCreateTime(new Date(System.currentTimeMillis()));	
	   this.getSession().save(user);
	}

	@Override
	public void delete(int id) {
	   	User user=(User)getSession().get(User.class,id);
	   	getSession().delete(user);
	}

	@Override
	public void update(User user) {
		getSession().update(user);
	}

	@Override
	public User findById(int id) {
		User user=(User)getSession().get(User.class,id);
		if (user==null) {
			return null;
		}
		return user;
	}

	@Override
	public List<User> findList(int type) {
		String hql = "from User where type='" +type+"'order by id";
		List<User> list = getHibernateTemplate().find(hql);
		return list;
	}
	
   
}
