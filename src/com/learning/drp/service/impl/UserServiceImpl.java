package com.learning.drp.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.learning.drp.domain.User;
import com.learning.drp.service.UserService;

public class UserServiceImpl extends HibernateDaoSupport implements UserService{

	@Override
	public boolean validateUser(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("here. userService......");
		String hql = "from User where username='" + username + "'and password='" + password +"'and isActive = " + 1;
        Query query = this.getSession().createQuery(hql);
        User user = new User();
        List<User> list = (List<User>) query.list();
        System.out.println(list);
        if (list.size() > 0) {
			return true;
		}
		return false;
	}

}
