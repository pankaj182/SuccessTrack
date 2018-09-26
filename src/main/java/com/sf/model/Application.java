package com.sf.model;

import com.sf.utilities.DateUtilities;

public class Application {
	private int applicationId;
	private int requisitionId;
	private int candidateId;
	private String creationDate;
	private String status;
	private boolean isActive;
	private byte[] resume;
	private byte[] coverLetter;

	private void initialize() {
		applicationId = 0;
		requisitionId = 0;
		candidateId = 0;
		creationDate = "";
		status = "";
		isActive = false;
		resume = null;
		coverLetter = null;
	}

	public Application(int requisitionId, int candidateId) {
		initialize();
		this.setCandidateId(candidateId);
		this.setRequisitionId(requisitionId);
		this.setCreationDate();
	}
	public Application(int applicationId, int requisitionId, int candidateId) {
		initialize();
		this.setApplicationId(applicationId);
		this.setCandidateId(candidateId);
		this.setRequisitionId(requisitionId);
		this.setCreationDate();
	}

	public int getApplicationId() {
		return applicationId;
	}

	public int getRequisitionId() {
		return requisitionId;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public String getStatus() {
		return status;
	}

	public boolean isActive() {
		return isActive;
	}

	public byte[] getResume() {
		return resume;
	}

	public byte[] getCoverLetter() {
		return coverLetter;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}

	public void setCoverLetter(byte[] coverLetter) {
		this.coverLetter = coverLetter;
	}

	private void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	private void setRequisitionId(int requisitionId) {
		this.requisitionId = requisitionId;
	}

	private void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	private void setCreationDate() {
		this.creationDate = DateUtilities.getCurrentDateAsString();
	}
}
