package com.learning.drp.domain;

import java.sql.Date;



/*
 * 文件
 */
public class Resourcedoc {
	
	private Integer id;
	
	private String resourceName;
	
	private String resourceDescription;
   
	private String resourcePath;
	
	private Integer resourceType;
	
	private Integer userId;
	
	private Date craeteTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceDescription() {
		return resourceDescription;
	}

	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCraeteTime() {
		return craeteTime;
	}

	public void setCraeteTime(Date craeteTime) {
		this.craeteTime = craeteTime;
	}
	
	
}
