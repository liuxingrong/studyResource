package com.learning.drp.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.learning.drp.domain.Weights;
import com.learning.drp.service.WeightsService;

public class WeightsServiceImpl extends HibernateDaoSupport implements
		WeightsService {

	@Override
	public void add(Weights weights) {
		this.getSession().save(weights);
	}

	@Override
	public List<Weights> findAll(Weights weights) {
		String hql = "from Weights where 1=1";
		Query query = this.getSession().createQuery(hql);
		List<Weights> list = (List<Weights>) query.list();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	@Override
	public Weights find(int id) {
		Weights weights = (Weights) this.getSession().get(Weights.class, id);
		if (weights == null) {
			return null;
		}
		return weights;
	}

	@Override
	public void update(Weights weights) {
		this.getSession().update(weights);
	}

	@Override
	public void del(Weights weights) {
		this.getSession().delete(weights);
	}

}
