package com.learning.drp.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.learning.drp.domain.Resourcedoc;
import com.learning.drp.service.ResourceDocService;

public class ResourceDocServiceImpl extends HibernateDaoSupport implements
		ResourceDocService {

	@Override
	public void add(Resourcedoc resourcedoc) {
		this.getSession().save(resourcedoc);
	}

	@Override
	public List<ResourceDocService> findAll(Resourcedoc resourcedoc) {
		String hql = "from Resourcedoc where 1=1";
		if (resourcedoc.getResourceType() != null) {
			hql += " and resourceType =" + resourcedoc.getResourceType();
		}
		Query query = this.getSession().createQuery(hql);
		List<ResourceDocService> list = (List<ResourceDocService>) query.list();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	@Override
	public ResourceDocService find(int id) {
		ResourceDocService resourceDoc = (ResourceDocService) this.getSession()
				.get(ResourceDocService.class, id);
		if (resourceDoc == null) {
			return null;
		}
		return resourceDoc;
	}

	@Override
	public void update(Resourcedoc resourcedoc) {
		this.getSession().update(resourcedoc);
	}

	@Override
	public void del(Resourcedoc resourcedoc) {
		this.getSession().delete(resourcedoc);
	}
}
