package com.learning.drp.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.learning.drp.domain.Score;
import com.learning.drp.service.ScoreService;

public class ScoreServiceImpl extends HibernateDaoSupport implements
		ScoreService {

	@Override
	public void add(Score score) {
		this.getSession().save(score);
	}

	@Override
	public List<Score> findAll(Score score) {
		String hql = "from Score where 1=1";
		if (score.getType() != null) {
			hql += " and type =" + score.getType();
		}
		if (score.getUserId() != null) {
			hql += " and userId =" + score.getUserId();
		}
		if (score.getDocId() != null) {
			hql += " and docId =" + score.getDocId();
		}
		Query query = this.getSession().createQuery(hql);
		List<Score> list = (List<Score>) query.list();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	@Override
	public Score find(int id) {
		Score score = (Score) this.getSession().get(Score.class, id);
		if (score == null) {
			return null;
		}
		return score;
	}

	@Override
	public void update(Score score) {
		this.getSession().update(score);
	}

	@Override
	public void del(Score score) {
		this.getSession().delete(score);
	}
}
