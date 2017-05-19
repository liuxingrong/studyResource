package com.learning.drp.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.learning.drp.domain.Testdoc;
import com.learning.drp.service.TestdocService;

public class TestdocServiceImpl extends HibernateDaoSupport implements
		TestdocService {

	@Override
	public void add(Testdoc testdoc) {
		this.getSession().save(testdoc);
	}

	@Override
	public List<Testdoc> findAll(Testdoc testdoc) {
		String hql = "from Testdoc where 1=1";
		if (testdoc.getUserId() != null) {
			hql += " and userId =" + testdoc.getUserId();
		}
		Query query = this.getSession().createQuery(hql);
		List<Testdoc> list = (List<Testdoc>) query.list();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	@Override
	public Testdoc find(int id) {
		Testdoc testdoc = (Testdoc) this.getSession().get(Testdoc.class, id);
		if (testdoc == null) {
			return null;
		}
		return testdoc;
	}

	@Override
	public void update(Testdoc testdoc) {
		this.getSession().update(testdoc);
	}

	@Override
	public void del(Testdoc testdoc) {
		this.getSession().delete(testdoc);
	}

}
