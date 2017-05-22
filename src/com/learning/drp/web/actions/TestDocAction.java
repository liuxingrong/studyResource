package com.learning.drp.web.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.learning.drp.domain.Testdoc;
import com.learning.drp.service.TestdocService;
import com.learning.drp.service.UserManageService;
import com.learning.drp.web.forms.FileUploadForm;
import com.learning.util.Result;
import com.learning.util.Utils;

public class TestDocAction extends DispatchAction {

	private TestdocService testdocService;

	private UserManageService userManageService;

	public void setTestdocService(TestdocService testdocService) {
		this.testdocService = testdocService;
	}
	
	public void setUserManageService(UserManageService userManageService) {
		this.userManageService = userManageService;
	}
	
	public ActionForward getData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		Result result = new Result();
		Testdoc testdoc = new Testdoc();
		try{
			List<Testdoc> list = testdocService.findAll(testdoc);
			List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
			for(int i=0;i<list.size();i++){
				Testdoc en = list.get(i);
				Map<String, Object> map = new HashedMap();
				map.put("id", en.getId());
				map.put("testDocName", en.getTestDocName());
				map.put("testDocDescription", en.getTestDocDescription());
				map.put("testDocPath", en.getTestDocPath());
				map.put("createTime", en.getCreateTime());
				map.put("realname", userManageService.findById(en.getUserId()).getRealname());
				list1.add(map);
			}
			result.setStatus(true);
			result.setData(list1);
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
		FileUploadForm fileUploadForm = (FileUploadForm)form;
		FormFile uploadFile = fileUploadForm.getfile();  
		String uploadPath = request.getRealPath("/upload");
		String fileName = new java.util.Date().getTime() + "*" + uploadFile.getFileName();
		
		try {
			// 上传文件
			File file = new File(uploadPath);
			if (!file.exists() && !file .isDirectory()) {
				// upload文件夹不存在
				 file.mkdir();
			}
	        FileOutputStream outer = new FileOutputStream(new File(uploadPath, fileName));  
	        byte[] buffer = uploadFile.getFileData();  
	        outer.write(buffer);  
	        outer.close();  
	        // 上传成功
			Result result = new Result();
			String testDocName = request.getParameter("name");
			String testDocDescription = request.getParameter("description");
			String testDocPath = fileName;
			String testDocAnswer = request.getParameter("answer");
			String userId = request.getParameter("userId");
			Testdoc testdoc = new Testdoc();
			try{
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
		} catch (Exception e) { 
	    	//上传失败
	    	log.error(e);
	    	response.getWriter().write(Utils.ObjToJson(new Result(false, "插入错误")));
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
	
	public ActionForward random(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setCharacterEncoding("utf-8");
		Testdoc testdoc = new Testdoc();
		Random random = new Random();
		Result result = new Result();
		try{
			List<Testdoc> list = testdocService.findAll(testdoc);
			int n = random.nextInt(list.size()-1);
			testdoc = list.get(n);
			result.setData(testdoc);
			result.setStatus(true);
			response.getWriter().write(Utils.ObjToJson(result));
		}catch(Exception e){
			result.setStatus(false);
			response.getWriter().write(Utils.ObjToJson(result));
		}
		return null;
	}
}
