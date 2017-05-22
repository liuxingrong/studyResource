package com.learning.drp.web.actions;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.learning.drp.domain.Score;
import com.learning.drp.domain.Weights;
import com.learning.drp.service.ScoreService;
import com.learning.drp.service.WeightsService;
import com.learning.util.Result;
import com.learning.util.Utils;

public class ScoreAction extends DispatchAction{
	private ScoreService scoreService;
	private WeightsService weightsService;
	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public void setWeightsService(WeightsService weightsService) {
		this.weightsService = weightsService;
	}

	public void getData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		Score score = new Score();
		Result result = new Result();
		try{
			List<Score> scoreList = scoreService.findAll(score);
			if(scoreList!=null){
				result.setStatus(true);
				result.setData(scoreList);
				response.getWriter().write(Utils.ObjToJson(result));
			}else{
				result.setStatus(false);
				response.getWriter().write(Utils.ObjToJson(result));
			}
		}catch(Exception e){
			log.error(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(new Result(false, "获取数据失败！")));
		}
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		Result result = new Result();
		Score score = new Score();
		try{
			if(id == null){
				throw new Exception("请选择要查看的课程！");
			}
			score = scoreService.find(Integer.valueOf(id));
			result.setData(score);
			result.setStatus(true);
			response.getWriter().write(Utils.ObjToJson(result));
		}catch(Exception e){
			result.setStatus(false);
			response.getWriter().write(Utils.ObjToJson(result));
		}
		return null;
	}
	
	/**
	 * 用来学生做题后添加成绩
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		String score = request.getParameter("score");
		String errorDetail = request.getParameter("errorDetail");
		String userId = request.getParameter("userId");
		String docId = request.getParameter("docId");

		try{
			Score entity = new Score();
			entity.setType(Integer.valueOf(type));
			entity.setScore(Integer.valueOf(score));
			entity.setErrorDetail(errorDetail);
			entity.setUserId(Integer.valueOf(userId));
			entity.setDocId(Integer.valueOf(docId));
			entity.setCreateTime(new Date(System.currentTimeMillis()));
			entity.setChangeTime(new Date(System.currentTimeMillis()));
			scoreService.add(entity);
			response.getWriter().write(Utils.ObjToJson(new Result(true, null)));
		}catch(Exception e){
			log.error(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(new Result(false, "插入错误")));
		}
		return null;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String score = request.getParameter("score");
		String errorDetail = request.getParameter("errorDetail");
		String userId = request.getParameter("userId");
		String docId = request.getParameter("docId");

		try{
			Score entity = new Score();
			entity.setType(Integer.valueOf(type));
			entity.setScore(Integer.valueOf(score));
			entity.setErrorDetail(errorDetail);
			entity.setUserId(Integer.valueOf(userId));
			entity.setDocId(Integer.valueOf(docId));
			entity.setChangeTime(new Date(System.currentTimeMillis()));
			scoreService.update(entity);
			response.getWriter().write(Utils.ObjToJson(new Result(true, null)));
		}catch(Exception e){
			log.error(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(new Result(false, "修改失败")));
		}
		return null;
	}
	
	/**
	 * 获取学生成绩列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getUserScore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		Result result = new Result();
		List<Map<String, Object>> list = scoreService.getUserScore();
		Weights weights = weightsService.find(1);
		if(list!=null){
			Map<String, Object> map = new HashedMap();
			map.put("score", list);
			map.put("weight", weights);
			result.setStatus(true);
			result.setData(map);
			response.getWriter().write(Utils.ObjToJson(result));
		}else{
			result.setStatus(false);
			response.getWriter().write(Utils.ObjToJson(result));
		}
		return null;
	}
	
	/**
	 * 修改学生成绩，type为1时清空学生成绩
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateStudy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String userId = request.getParameter("id");
		String type = request.getParameter("type");
		String studyScore = request.getParameter("study");
		String testScore = request.getParameter("test");
		String resourceScore = request.getParameter("practice");
		String projectScore = request.getParameter("project");
		if(type.equals("1")){
			studyScore = "0";
			testScore = "0";
			resourceScore = "0";
			projectScore = "0";
		}
		try{
			Score entity = new Score();
			entity.setUserId(Integer.valueOf(userId));
			List<Score> list = scoreService.findAll(entity);
			if(list==null){
				throw new Exception("修改失败！");
			}
			for(int i=0;i<list.size();i++){
				Score score = list.get(i);
				if(score.getType()==1){
					score.setScore(Integer.valueOf(studyScore));
					scoreService.update(score);
				}
				if(score.getType()==2){
					score.setScore(Integer.valueOf(testScore));
					scoreService.update(score);
				}
				if(score.getType()==3){
					score.setScore(Integer.valueOf(projectScore));
					scoreService.update(score);
				}
				if(score.getType()==4){
					score.setScore(Integer.valueOf(resourceScore));
					scoreService.update(score);
				}
			}
			response.getWriter().write(Utils.ObjToJson(new Result(true, null)));
		}catch(Exception e){
			log.error(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(new Result(false, "修改失败")));
		}
		return null;
	}
	
	public ActionForward delStudy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		try{
			Score entity = new Score();
			entity.setUserId(Integer.valueOf(userId));
			List<Score> list = scoreService.findAll(entity);
			if(list==null){
				throw new Exception("删除失败！");
			}
			for(int i=0;i<list.size();i++){
				Score score = list.get(i);
				scoreService.del(score);	
			}
			response.getWriter().write(Utils.ObjToJson(new Result(true, null)));
		}catch(Exception e){
			log.error(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(new Result(false, "删除失败")));
		}
		return null;
	}
	
	/**
	 * 学生查看成绩
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findScoreByUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String userId = request.getParameter("id");
		Result result = new Result();
		Weights weights = weightsService.find(1);
		try{
			Score entity = new Score();
			entity.setUserId(Integer.valueOf(userId));
			List<Score> list = scoreService.findAll(entity);
			if(list==null){
				throw new Exception("获取数据失败！");
			}
			Map<String, Object> map = new HashedMap();
			int sum = 0;
			for(int i=0;i<list.size();i++){
				Score score = list.get(i);
				if(score.getType()==1){
					map.put("score1", score.getScore());
					sum += score.getScore()*weights.getStudyWeights();
				}
				if(score.getType()==2){
					map.put("score2", score.getScore());
					sum +=score.getScore()*weights.getTestWeights();
				}
				if(score.getType()==3){
					map.put("score3", score.getScore());
					sum +=score.getScore()*weights.getProjectWeights();
				}
				if(score.getType()==4){
					map.put("score4", score.getScore());
					sum +=score.getScore()*weights.getPracticeWeights();
				}
			}
			map.put("score", sum);
			result.setStatus(true);
			result.setData(map);
			response.getWriter().write(Utils.ObjToJson(result));
		}catch(Exception e){
			result.setStatus(false);
			log.error(e.getMessage());
			result.setData(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(new Result(false, "获取失败！")));
		}
		return null;
	}
	
	public ActionForward isScore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		Score score = new Score();
		score.setUserId(Integer.parseInt(userId));
		score.setType(Integer.parseInt(type));
		List<Score> list = scoreService.findAll(score);
		Result result = new Result();
		if(list!=null){
			score = list.get(0);
			if(score.getScore()>0){
				result.setStatus(true);
				result.setData(false);
			}else{
				result.setStatus(true);
				result.setData(true);
			}
		}else{
			result.setStatus(true);
			result.setData(true);
		}
		response.getWriter().write(Utils.ObjToJson(result));
		return null;
	}
}
