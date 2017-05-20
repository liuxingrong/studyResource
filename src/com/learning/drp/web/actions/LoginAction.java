package com.learning.drp.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.learning.drp.domain.User;
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
		User user = userService.validateUser(username, password);
		if(user != null){
			//登录成功
			user.setPassword(null);
			request.getSession().setAttribute("user", user);
			Result result = new Result();
			result.setStatus(true);
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
		response.setCharacterEncoding("utf-8");
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			System.out.println(user);
			response.getWriter().write(Utils.ObjToJson(new Result(true, user)));
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
	
	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(!userService.isRegister(username)){
			try {
				userService.register(username, password);
				response.getWriter().write(Utils.ObjToJson(new Result(true, null)));
			} catch (Exception e) {
				log.error(e.getMessage());
				response.getWriter().write(Utils.ObjToJson(new Result(false, "插入错误")));
			}
			
		} else {
			response.getWriter().write(Utils.ObjToJson(new Result(false, "该用户已存在，请重新注册")));
		}
		return null;
	}
}
