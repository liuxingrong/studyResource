package com.learning.util;

public class Result {
	
	private boolean status;
	private Object data;
	private String msg;
	
	public Result() {
		// TODO Auto-generated constructor stub
	}
	
	public Result(boolean status, Object data) {
		this.status = status;
		this.data = data;
	}
	
	public Result(boolean status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public Result(boolean status, Object data, String msg) {
		this.status = status;
		this.data = data;
		this.msg = msg;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
