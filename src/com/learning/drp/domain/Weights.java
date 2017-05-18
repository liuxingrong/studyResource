package com.learning.drp.domain;

public class Weights {
	private int id;
	private double studyWeights;
	private double testWeigths;
	private double practiceWeights;
	private double projectWeights;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getStudyWeights() {
		return studyWeights;
	}
	public void setStudyWeights(double studyWeights) {
		this.studyWeights = studyWeights;
	}
	public double getTestWeigths() {
		return testWeigths;
	}
	public void setTestWeigths(double testWeigths) {
		this.testWeigths = testWeigths;
	}
	public double getPracticeWeights() {
		return practiceWeights;
	}
	public void setPracticeWeights(double practiceWeights) {
		this.practiceWeights = practiceWeights;
	}
	public double getProjectWeights() {
		return projectWeights;
	}
	public void setProjectWeights(double projectWeights) {
		this.projectWeights = projectWeights;
	}
	@Override
	public String toString() {
		return "weights [id=" + id + ", studyWeights=" + studyWeights
				+ ", testWeigths=" + testWeigths + ", practiceWeights="
				+ practiceWeights + ", projectWeights=" + projectWeights
				+ ", getId()=" + getId() + ", getStudyWeights()="
				+ getStudyWeights() + ", getTestWeigths()=" + getTestWeigths()
				+ ", getPracticeWeights()=" + getPracticeWeights()
				+ ", getProjectWeights()=" + getProjectWeights()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
