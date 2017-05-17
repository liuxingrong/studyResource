package com.learning.test;

import com.learning.drp.web.forms.LoginActionForm;

import net.sf.json.JSONObject;

public class Test {
	public static void main(String[] args) {
		LoginActionForm loginActionForm = new LoginActionForm();
		loginActionForm.setUsername("dengzhuo");
		loginActionForm.setPassword("123123");
		System.out.println(loginActionForm);
		JSONObject jsonObject = JSONObject.fromObject(loginActionForm);
		String jsString = jsonObject.toString();
		System.out.println(jsString);
	}
}	
