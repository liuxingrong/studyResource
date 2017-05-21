package com.learning.drp.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.learning.drp.domain.Studylink;
import com.learning.drp.service.StudylinkService;

public class StudylinkServiceImpl extends HibernateDaoSupport implements
		StudylinkService {

	@Override
	public void add(Studylink studylink) {
		this.getSession().save(studylink);
	}

	@Override
	public List<Studylink> findAll(Studylink studylink) {
		String hql = "from Studylink where 1=1";
		if (studylink.getUserId() != null) {
			hql += " and userId =" + studylink.getUserId();
		}
		Query query = this.getSession().createQuery(hql);
		List<Studylink> list = (List<Studylink>) query.list();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	@Override
	public Studylink find(int id) {
		Studylink studylink = (Studylink) this.getSession().get(
				Studylink.class, id);
		if (studylink == null) {
			return null;
		}
		return studylink;
	}

	@Override
	public void update(Studylink studylink) {
		this.getSession().update(studylink);
	}

	@Override
	public void del(Studylink studylink) {
		this.getSession().delete(studylink);
	}

}
