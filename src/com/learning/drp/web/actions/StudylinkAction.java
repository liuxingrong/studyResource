package com.learning.drp.web.actions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.learning.drp.domain.Studylink;
import com.learning.drp.service.StudylinkService;
import com.learning.drp.service.UserManageService;
import com.learning.util.Result;
import com.learning.util.Utils;

public class StudylinkAction extends DispatchAction {
	
	private StudylinkService studylinkService;

	private UserManageService userManageService;
	
	public void setStudylinkService(StudylinkService studylinkService) {
		this.studylinkService = studylinkService;
	}
	
	public void setUserManageService(UserManageService userManageService) {
		this.userManageService = userManageService;
	}
	
	public ActionForward getData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		Studylink studylink = new Studylink();
		Result result = new Result();
		try{
			List<Studylink> list = studylinkService.findAll(studylink);
			List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
			for(int i=0;i<list.size();i++){
				Studylink en = list.get(i);
				Map<String, Object> map = new HashedMap();
				map.put("id", en.getId());
				map.put("linkName", en.getLinkName());
				map.put("linkDescription", en.getLinkDescription());
				map.put("linkUrl", en.getLinkUrl());
				map.put("createTime", en.getCreateTime());
				map.put("realname", userManageService.findById(en.getUserId()).getRealname());
				list1.add(map);
			}
			result.setStatus(true);
			result.setData(list1);
			response.getWriter().write(Utils.ObjToJson(result));
		}catch(Exception e){
			log.error(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(new Result(false, "获取错误！")));
		}
		return null;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		Studylink studylink = new Studylink();
		Result result = new Result();
		try{
			studylink = studylinkService.find(Integer.valueOf(id));
			result.setStatus(true);
			result.setData(studylink);
			response.getWriter().write(Utils.ObjToJson(result));
		}catch(Exception e){
			log.error(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(new Result(false, "获取错误！")));
		}
		return null;
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String linkName = request.getParameter("linkName");
		String linkDescription = request.getParameter("linkDescription");
		String linkUrl = request.getParameter("linkUrl");
		String userId = request.getParameter("userId");
		Studylink studylink = new Studylink();
		Result result = new Result();
		try{
			if(linkName==null){
				throw new Exception("学习链接名不能为空！");
			}
			if(linkUrl==null){
				throw new Exception("学习链接地址不能为空！");
			}
			if(userId==null){
				throw new Exception("登录超时！");
			}
			studylink.setLinkDescription(linkDescription);
			studylink.setLinkName(linkName);
			studylink.setLinkUrl(linkUrl);
			studylink.setUserId(Integer.valueOf(userId));
			studylink.setCreateTime(new Date(System.currentTimeMillis()));
			studylinkService.add(studylink);
			result.setStatus(true);
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
		String linkName = request.getParameter("linkName");
		String linkDescription = request.getParameter("linkDescription");
		String linkUrl = request.getParameter("linkUrl");
		Studylink studylink = new Studylink();
		Result result = new Result(); 
		try{
			if(linkName==null){
				throw new Exception("学习链接名不能为空！");
			}
			if(linkUrl==null){
				throw new Exception("学习链接地址不能为空！");
			}
			studylink = studylinkService.find(Integer.valueOf(id));
			studylink.setLinkDescription(linkDescription);
			studylink.setLinkName(linkName);
			studylink.setLinkUrl(linkUrl);
			studylinkService.update(studylink);
			result.setStatus(true);
			response.getWriter().write(Utils.ObjToJson(result));
		}catch(Exception e){
			result.setStatus(false);
			result.setData(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(result));
		}
		return null;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		Studylink studylink = new Studylink();
		Result result = new Result(); 
		try{
			studylink.setId(Integer.valueOf(id));
			studylinkService.del(studylink);
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
