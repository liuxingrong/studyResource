package com.learning.drp.service;

import java.util.List;

import com.learning.drp.domain.Testdoc;

/**
 * 在线测试
 * @author lxr
 *
 */
public interface TestdocService {
	
	public void add(Testdoc testdoc);
	
	public List<Testdoc> findAll(Testdoc testdoc);
	
	public Testdoc find(int id);
	
	public void update(Testdoc testdoc);
	
	public void del(Testdoc testdoc);
}
