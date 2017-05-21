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

import com.learning.drp.domain.Resourcedoc;
import com.learning.drp.service.ResourceDocService;
import com.learning.drp.service.UserManageService;
import com.learning.util.Result;
import com.learning.util.Utils;

public class ResourceDocAction extends DispatchAction{
	
	private ResourceDocService resourceDocService;
	
	private UserManageService userManageService;
	
	public void setResourceDocService(ResourceDocService resourceDocService) {
		this.resourceDocService = resourceDocService;
	}
	
	public void setUserManageService(UserManageService userManageService) {
		this.userManageService = userManageService;
	}

	public ActionForward getData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		Resourcedoc resourcedoc = new Resourcedoc();
		resourcedoc.setResourceType(Integer.valueOf(type));
		List<Resourcedoc> resourcedocList = resourceDocService.findAll(resourcedoc);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Result result = new Result();
		if(resourcedocList!=null){
			for(int i=0;i<resourcedocList.size();i++){
				Resourcedoc en = resourcedocList.get(i);
				Map<String, Object> map = new HashedMap();
				map.put("id", en.getId());
				map.put("resourceName", en.getResourceName());
				map.put("resourceDescription", en.getResourceDescription());
				map.put("createDate", en.getCraeteTime());
				map.put("realname", userManageService.findById(en.getUserId()).getRealname());
				list.add(map);
			}
			result.setStatus(true);
			result.setData(list);
			response.getWriter().write(Utils.ObjToJson(result));
		}else{
			result.setStatus(false);
			response.getWriter().write(Utils.ObjToJson(result));
		}
		return null;
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String resourceName = request.getParameter("resourceName");
		String resourceDescription = request.getParameter("resourceDescription");
		String resourcePath = request.getParameter("resourcePath");
		String resourceType = request.getParameter("resourceType");
		String userId = request.getParameter("userId");
		try{
			if(resourceName==null){
				throw new Exception("课程名不能为空！");
			}
			if(resourceType==null){
				throw new Exception("请选择导入的课程类型！");
			}
			if(userId==null){
				throw new Exception("登录超时！");
			}
			Resourcedoc resourcedoc = new Resourcedoc();
			resourcedoc.setResourceName(resourceName);
			resourcedoc.setResourceDescription(resourceDescription);
			resourcedoc.setUserId(Integer.valueOf(userId));
			resourcedoc.setResourceType(Integer.valueOf(resourceType));
			resourcedoc.setCraeteTime(new Date(System.currentTimeMillis()));
			resourcedoc.setResourcePath(resourcePath);
			resourceDocService.add(resourcedoc);
			response.getWriter().write(Utils.ObjToJson(new Result(true, null)));
		}catch(Exception e){
			log.error(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(new Result(false, "插入错误")));
		}
		return null;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String resourceName = request.getParameter("resourceName");
		String resourceDescription = request.getParameter("resourceDescription");
		Resourcedoc resourcedoc = new Resourcedoc();
		resourcedoc = resourceDocService.find(Integer.valueOf(id));
		resourcedoc.setResourceName(resourceName);
		resourcedoc.setResourceDescription(resourceDescription);
		try{
			if(id==null){
				throw new Exception("请选择要修改的课程");
			}
			resourceDocService.update(resourcedoc);
			response.getWriter().write(Utils.ObjToJson(new Result(true, null)));
		}catch(Exception e){
			log.error(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(new Result(false, "修改失败！")));
		}
		return null;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		Resourcedoc resourcedoc = new Resourcedoc();
		resourcedoc.setId(Integer.valueOf(id));
		try{
			if(id == null){
				throw new Exception("请选择要删除的课程");
			}
			resourceDocService.del(resourcedoc);
			response.getWriter().write(Utils.ObjToJson(new Result(true, null)));
		}catch(Exception e){
			log.error(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(new Result(false, "删除失败！")));
		}
		return null;
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		Resourcedoc resourcedoc = new Resourcedoc();
		Result result = new Result();
		try{
			if(id == null){
				throw new Exception("请选择课程");
			}
			resourcedoc = resourceDocService.find(Integer.valueOf(id));
			result.setData(resourcedoc);
			result.setStatus(true);
			response.getWriter().write(Utils.ObjToJson(result));
		}catch(Exception e){
			result.setStatus(false);
			response.getWriter().write(Utils.ObjToJson(result));
		}
		return null;
	}
}
