package com.learning.drp.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.learning.drp.service.StudylinkService;

public class StudylinkAction extends DispatchAction {
	
	private StudylinkService studylinkService;

	public void setStudylinkService(StudylinkService studylinkService) {
		this.studylinkService = studylinkService;
	}
	
	public ActionForward getData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		return null;
	}

}
