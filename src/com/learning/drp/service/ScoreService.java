package com.learning.drp.service;

import java.util.List;
import com.learning.drp.domain.Score;

/**
 * 成绩类
 * @author lxr
 *
 */
public interface ScoreService {
	
	public void add(Score score);
	
	public List<Score> findAll(Score score);
	
	public Score find(int id);
	
	public void update(Score score);
	
	public void del(Score score);

}
