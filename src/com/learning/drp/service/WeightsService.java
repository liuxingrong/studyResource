package com.learning.drp.service;

import java.util.List;
import com.learning.drp.domain.Weights;

/**
 * 成绩权值
 * @author lxr
 *
 */
public interface WeightsService {
	
	public void add(Weights weights);
	
	public List<Weights> findAll(Weights weights);
	
	public Weights find(int id);
	
	public void update(Weights weights);
	
	public void del(Weights weights);
}
