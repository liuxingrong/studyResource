package com.learning.drp.web.actions;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.learning.drp.domain.Score;
import com.learning.drp.domain.User;
import com.learning.drp.enums.IsActive;
import com.learning.drp.service.ScoreService;
import com.learning.drp.service.UserManageService;
import com.learning.util.Result;
import com.learning.util.Utils;

public class UserManageAction extends DispatchAction{
	private ScoreService scoreService;
	
	private UserManageService userManageService;
    
	public void setUserManageService(UserManageService userManageService){
		this.userManageService=userManageService;
	}
	

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}
	
	/*
	 * 查询列表
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("utf-8");
		int type=Integer.parseInt(request.getParameter("type"));
		List<User> userList=userManageService.findList(type);
		Result result = new Result();
		if(userList!=null){
			result.setStatus(true);
			result.setData(userList);
			response.getWriter().write(Utils.ObjToJson(result));
		}else {
			response.getWriter().write(Utils.ObjToJson(new Result(false, "暂无数据！")));
		}
		return null;
	}
	
	/*
	 * 删除用户
	 */
	public ActionForward delUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		int id=Integer.parseInt(request.getParameter("id"));
		Score score=new Score();
		score.setUserId(id);
		List<Score>scoreList=scoreService.findAll(score);
		for(Score score2:scoreList){
			scoreService.del(score2);
		}
		userManageService.delete(id);
		response.getWriter().write(Utils.ObjToJson(new Result(true, "删除成功")));
		return null;
	}
	
	/*
	 * 判断用户是否存在
	 */
	
	public ActionForward isRegister(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		System.out.println("----------"+username);
		User user=userManageService.findByUserName(username);
		System.out.println("---------"+user.toString());
		Result result=new Result();
		if(user.getId()==null){
			result.setStatus(true);
		}
		else {
			result.setMsg("用户已存在");
			result.setStatus(false);
		}
		response.getWriter().write(Utils.ObjToJson(result));
		return null;
	}
	/*
	 * 添加人员
	 */
	public ActionForward addUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		String realname=request.getParameter("realname");
		String gender=request.getParameter("gender");
		String email=request.getParameter("email");
		String tel=request.getParameter("tel");
		int type=Integer.parseInt(request.getParameter("type"));
        User user=new User();
		user.setNumber(username);
		user.setRealname(realname);
		user.setGender(gender);
		user.setEmail(email);
		user.setTel(tel);
		user.setType(type);
		user.setIsActive(1);
		user.setUsername(username);
		user.setPassword("123456");
		userManageService.add(user);
		//初始化学生成绩
		if (type==1) {
			int userId=userManageService.findByUserName(username).getId();
			
			for (int i =1; i <= 4; i++) {
				Score score=new Score();
				score.setScore(0);
				score.setCreateTime(new Date(System.currentTimeMillis()));
				score.setUserId(userId);
				score.setType(i);
				scoreService.add(score);
			}
			
		}
      
		response.getWriter().write(Utils.ObjToJson(new Result(true, "添加成功")));
		return null;
	}
	/*
	 * 停用,启用
	 */
	public ActionForward changeActive(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		int id=Integer.parseInt(request.getParameter("id"));
		int isActive=Integer.parseInt(request.getParameter("isActive"));
		User user=userManageService.findById(id);
		if (isActive==1) {
			user.setIsActive(IsActive.UNACTIVATED.getValue());
			userManageService.update(user);
			response.getWriter().write(Utils.ObjToJson(new Result(true, "停用成功")));
		}
		if (isActive==0) {
			user.setIsActive(IsActive.ACTIVATION.getValue());
			userManageService.update(user);
			response.getWriter().write(Utils.ObjToJson(new Result(true, "启用成功")));
		}
		
		return null;
	} 

	
	
	
	/*
	 * 进入编辑
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		int id=Integer.parseInt(request.getParameter("id"));
		User user=userManageService.findById(id);
		Result result=new Result();
		result.setData(user);
		response.getWriter().write(Utils.ObjToJson(result));
		return null;
	} 
	/*
	 * 修改提交
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		String realname=request.getParameter("realname");
		String gender=request.getParameter("gender");
		String email=request.getParameter("email");
		String tel=request.getParameter("tel");
		User user=userManageService.findById(id);
		user.setRealname(realname);
		user.setGender(gender);
		user.setEmail(email);
		user.setTel(tel);
		userManageService.update(user);
		response.getWriter().write(Utils.ObjToJson(new Result(true,"修改成功")));
		return null;
	} 
	
	/*
	 * 重置密码
	 */
	public ActionForward resetPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		User user=userManageService.findById(id);
		user.setPassword("123456");
		userManageService.update(user);
		response.getWriter().write(Utils.ObjToJson(new Result(true,"重置成功")));
		return null;
	} 
	
	/*
	 * 修改密码
	 */
	public ActionForward editPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		String password=request.getParameter("password");
		User user=userManageService.findById(id);
		if (password.equals(user.getPassword())) {
			response.getWriter().write(Utils.ObjToJson(new Result(false,"和当前密码一致")));
		}
		user.setPassword(password);
		userManageService.update(user);
		response.getWriter().write(Utils.ObjToJson(new Result(true,"修改成功")));
		return null;
	} 
}
