package com.learning.drp.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.learning.drp.service.ScoreService;

public class ScoreAction extends DispatchAction{
	private ScoreService scoreService;

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public void getData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		System.out.println(11);
	}
	
}
