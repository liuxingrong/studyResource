package com.learning.drp.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.learning.drp.service.UserService;
import com.learning.util.Result;
import com.learning.util.Utils;

public class LoginAction extends DispatchAction {
	
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean falg = userService.validateUser(username, password);
		System.out.println(falg);
		if(falg){
			//登录成功
			request.getSession().setAttribute("user", username);
			Result result = new Result();
			result.setStatus(true);
			result.setData(true);
			response.getWriter().write(Utils.ObjToJson(result));
		}else {
			//登录失败
			Result result = new Result();
			result.setStatus(false);
			response.getWriter().write(Utils.ObjToJson(result));
		}
		return null;
	}
	
	public ActionForward validateUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			if(request.getSession().getAttribute("user") != null) {
				System.out.println(request.getSession().getAttribute("user"));
				response.getWriter().write(Utils.ObjToJson(new Result(true, null)));
			}else{
				response.getWriter().write(Utils.ObjToJson(new Result(false, null)));
			}
		return null;
	}
	
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("session is invalidating");
		request.getSession().invalidate();
		response.getWriter().write(Utils.ObjToJson(new Result(true, null)));
		return null;
	}
	
}
