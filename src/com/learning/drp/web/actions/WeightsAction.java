package com.learning.drp.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.learning.drp.domain.Weights;
import com.learning.drp.service.WeightsService;
import com.learning.util.Result;
import com.learning.util.Utils;

public class WeightsAction extends DispatchAction{

	private WeightsService weightsService;

	public void setWeightsService(WeightsService weightsService) {
		this.weightsService = weightsService;
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		Result result = new Result();
		Weights weights = new Weights();
		try{
			weights = weightsService.find(1);
			result.setStatus(true);
			result.setData(weights);
			response.getWriter().write(Utils.ObjToJson(result));
		}catch(Exception e){
			result.setStatus(false);
			result.setData(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(result));
		}
		return null;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String studyWeights = request.getParameter("studyWeights");
		String testWeigths = request.getParameter("testWeights");
		String practiceWeights = request.getParameter("practiceWeights");
		String projectWeights = request.getParameter("projectWeights");
		Result result = new Result();
		Weights weights = new Weights();
		try{
			weights = weightsService.find(1);
			weights.setPracticeWeights(Double.valueOf(practiceWeights));
			weights.setProjectWeights(Double.valueOf(projectWeights));
			weights.setStudyWeights(Double.valueOf(studyWeights));
			weights.setTestWeights(Double.valueOf(testWeigths));
			weightsService.update(weights);
			result.setStatus(true);
			response.getWriter().write(Utils.ObjToJson(result));
		}catch(Exception e){
			result.setStatus(false);
			result.setData(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(result));
		}
		return null;
	}
}
