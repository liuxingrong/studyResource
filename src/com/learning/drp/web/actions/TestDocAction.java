package com.learning.drp.web.actions;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.learning.drp.domain.Testdoc;
import com.learning.drp.service.TestdocService;
import com.learning.util.Result;
import com.learning.util.Utils;

public class TestDocAction extends DispatchAction {

	private TestdocService testdocService;

	public void setTestdocService(TestdocService testdocService) {
		this.testdocService = testdocService;
	}
	
	public ActionForward getData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		Result result = new Result();
		Testdoc testdoc = new Testdoc();
		try{
			List<Testdoc> list = testdocService.findAll(testdoc);
			result.setStatus(true);
			result.setData(list);
			response.getWriter().write(Utils.ObjToJson(result));
		}catch(Exception e){
			result.setStatus(false);
			result.setData(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(result));
		}
		return null;
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		Result result = new Result();
		Testdoc testdoc = new Testdoc();
		try{
			testdoc = testdocService.find(Integer.valueOf(id));
			result.setStatus(true);
			result.setData(testdoc);
			response.getWriter().write(Utils.ObjToJson(result));
		}catch(Exception e){
			result.setStatus(false);
			result.setData(e.getMessage());
			response.getWriter().write(Utils.ObjToJson(result));
		}
		return null;
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		Result result = new Result();
		String testDocName = request.getParameter("testDocName");
		String testDocDescription = request.getParameter("testDocDescription");
		String testDocPath = request.getParameter("testDocPath");
		String testDocAnswer = request.getParameter("testDocAnswer");
		String userId = request.getParameter("userId");
		Testdoc testdoc = new Testdoc();
		try{
			if(testDocName==null){
				throw new Exception("测试卷名不能为空！");
			}
			if(testDocPath==null){
				throw new Exception("上传测试卷！");
			}
			if(testDocAnswer==null){
				throw new Exception("测试卷答案不能为空！");
			}
			if(userId==null){
				throw new Exception("登录超时！");
			}
			testdoc.setTestDocName(testDocName);
			testdoc.setTestDocAnswer(testDocAnswer);
			testdoc.setTestDocDescription(testDocDescription);
			testdoc.setTestDocPath(testDocPath);
			testdoc.setUserId(Integer.valueOf(userId));
			testdoc.setCreateTime(new Date(System.currentTimeMillis()));
			testdocService.add(testdoc);
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
		Result result = new Result();
		String id = request.getParameter("id");
		String testDocName = request.getParameter("testDocName");
		String testDocDescription = request.getParameter("testDocDescription");
		String testDocAnswer = request.getParameter("testDocAnswer");
		Testdoc testdoc = new Testdoc();
		try{
			if(testDocName==null){
				throw new Exception("测试卷名不能为空！");
			}
			if(testDocAnswer==null){
				throw new Exception("测试卷答案不能为空！");
			}
			testdoc = testdocService.find(Integer.valueOf(id));
			testdoc.setTestDocName(testDocName);
			testdoc.setTestDocAnswer(testDocAnswer);
			testdoc.setTestDocDescription(testDocDescription);
			testdocService.update(testdoc);
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
		Result result = new Result(); 
		Testdoc testdoc = new Testdoc();
		try{
			testdoc.setId(Integer.valueOf(id));
			testdocService.del(testdoc);
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
