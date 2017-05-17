package com.learning.drp.web.actions;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.learning.util.Result;
import com.learning.util.Utils;

import net.sf.json.JSONObject;


public class LoginAction extends DispatchAction {
	
	private Result result = new Result();

	public ActionForward loginValidate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+"  "+password);
		if ("admin".equals(username) && "admin".equals(password)) {
			String remoteAddr = request.getRemoteAddr();
			System.out.println("remoteAddr==" + remoteAddr);
			String[] allowIps = request.getSession().getServletContext().getInitParameter("allow_ip").split(",");
			Arrays.sort(allowIps);
			if (Arrays.binarySearch(allowIps, remoteAddr) < 0) {
				return mapping.findForward("index");
			} 
			//登录成功
			request.getSession().setAttribute("user", username);
			
			result.setStatus(true);
			result.setData(true);
		}else {
			//登录失败
			result.setStatus(false);
		}
		response.getWriter().write(Utils.ObjToJson(result));
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
