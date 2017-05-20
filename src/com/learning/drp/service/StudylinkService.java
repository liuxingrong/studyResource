package com.learning.drp.service;

import java.util.List;

import com.learning.drp.domain.Studylink;

/**
 * 学习链接
 * @author lxr
 *
 */
public interface StudylinkService {
	
	public void add(Studylink studylink);
	
	public List<Studylink> findAll(Studylink studylink);
	
	public Studylink find(int id);
	
	public void update(Studylink studylink);
	
	public void del(Studylink studylink);
}
