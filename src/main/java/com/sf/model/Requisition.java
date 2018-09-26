package com.sf.model;

import com.sf.utilities.DateUtilities;

public class Requisition {
	private int requisitonId;
	private int originatorId;
	private String jobTitle;
	private String jobDescription;
	private int ctc;
	private String skillsRequired;
	private boolean isActive;
	private String startDate;
	private String endDate;
	private String creationDate;
	private int applicationCount;

	private void initialize() {
		requisitonId = 0;
		originatorId = 0;
		jobDescription = "";
		jobTitle = "";
		ctc = 0;
		skillsRequired = "";
		isActive = false;
		startDate = "";
		endDate = "";
		creationDate = "";
		setApplicationCount(0);
	}

	public Requisition() {
		initialize();
		this.setCreationDate();
	}

	public Requisition(int requisitionId, int originatorId) {
		initialize();
		this.setCreationDate();
		this.setOriginatorId(originatorId);
		this.setRequisitonId(requisitionId);
	}

	public Requisition(int requisitionId, int originatorId, String jobTitle) {
		initialize();
		this.setCreationDate();
		this.setOriginatorId(originatorId);
		this.setRequisitonId(requisitionId);
		this.setJobTitle(jobTitle);
	}

	public int getRequisitonId() {
		return requisitonId;
	}

	public int getOriginatorId() {
		return originatorId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public int getCtc() {
		return ctc;
	}

	public String getSkillsRequired() {
		return skillsRequired;
	}

	public boolean isActive() {
		return isActive;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getCreationDate() {
		return creationDate;
	}

	private void setRequisitonId(int requisitonId) {
		this.requisitonId = requisitonId;
	}

	public void setOriginatorId(int originatorId) {
		this.originatorId = originatorId;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public void setCtc(int ctc) {
		this.ctc = ctc;
	}

	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	private void setCreationDate() {
		this.creationDate = DateUtilities.getCurrentDateAsString();
	}

	public void setCreationDate(String date) {
		this.creationDate = date;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getApplicationCount() {
		return applicationCount;
	}

	public void setApplicationCount(int applicationCount) {
		this.applicationCount = applicationCount;
	}

}