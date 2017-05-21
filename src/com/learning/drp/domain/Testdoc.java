package com.learning.drp.domain;

import java.sql.Date;


public class Testdoc {
	private int id;
	private String testDocName;
	private String testDocPath;
	private String testDocAnswer;
	private String testDocDescription;
	private Integer userId;
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
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTestDocAnswer() {
		return testDocAnswer;
	}
	public void setTestDocAnswer(String testDocAnswer) {
		this.testDocAnswer = testDocAnswer;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTestDocDescription() {
		return testDocDescription;
	}
	public void setTestDocDescription(String testDocDescription) {
		this.testDocDescription = testDocDescription;
	}
	
}
