package com.learning.drp.service;

import java.util.List;

import com.learning.drp.domain.Resourcedoc;

/**
 * 实践任务和安装部署
 * @author lxr
 *
 */
public interface ResourceDocService {

	/**
	 * 添加
	 * @param resourceDoc
	 */
	public void add(Resourcedoc resourcedoc); 
	
	/**
	 * 通过类型获取所有数据
	 * @param resourceType
	 * @return
	 */
	public List<Resourcedoc> findAll(Resourcedoc resourcedoc);
	
	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public Resourcedoc find(int id);
	
	/**
	 * 修改
	 * @param resourceDoc
	 */
	public void update(Resourcedoc resourcedoc);
	
	/**
	 * 删除
	 * @param resourceDoc
	 */
	public void del(Resourcedoc resourcedoc);
}
