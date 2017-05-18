package com.learning.drp.domain;

import java.util.Date;

public class Testdoc {
	private int id;
	private String testDocName;
	private String testDocPath;
	private String testDocAnswer;
	private int userId;
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTestDocName() {
		return testDocName;
	}
	public void setTestDocName(String testDocName) {
		this.testDocName = testDocName;
	}
	public String getTestDocPath() {
		return testDocPath;
	}
	public void setTestDocPath(String testDocPath) {
		this.testDocPath = testDocPath;
	}
	public String getTestDocAnswer() {
		return testDocAnswer;
	}
	public void setTestDocAnswer(String testDocAnswer) {
		this.testDocAnswer = testDocAnswer;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Testdoc [id=" + id + ", testDocName=" + testDocName
				+ ", testDocPath=" + testDocPath + ", testDocAnswer="
				+ testDocAnswer + ", userId=" + userId + ", createTime="
				+ createTime + ", getId()=" + getId() + ", getTestDocName()="
				+ getTestDocName() + ", getTestDocPath()=" + getTestDocPath()
				+ ", getTestDocAnswer()=" + getTestDocAnswer()
				+ ", getUserId()=" + getUserId() + ", getCreateTime()="
				+ getCreateTime() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
