package com.learning.drp.service;


import java.util.List;

import com.learning.drp.domain.User;

/**
 * 用户管理
 *
 */
public interface UserManageService {
    /*
     * 添加学生
     */
	public void add(User user);
	
	/*
	 * 删除
	 */
	public void delete(int id);
	
	/**
	 * 修改
	 * @param id
	 */
	public void update(User user);
	 
	/*
	 * 查询
	 */
    public User findById(int id);
    
    /*
     * 查询人员列表
     */
    public List<User> findList(int type);
    
    /*
     * 通过用户名查询用户
     */
    public User findByUserName(String username);
}
