package com.sf.model;

public class CandidateBackground {
	private int candidateId;
	private String infoType;
	private String startDate;
	private String endDate;
	private String city;
	private String state;
	private String country;
	private String degree;
	private String major;
	private double cgpa;
	private double percentage;
	private String instituteName;
	private String jobTitle;
	private String companyName;
	private int salary;
	private String skillSet;

	private void initialize() {
		infoType = "";
		startDate = "";
		endDate = "";
		city = "";
		state = "";
		country = "";
		degree = "";
		major = "";
		cgpa = 0.0;
		percentage = 0.0;
		instituteName = "";
		jobTitle = "";
		companyName = "";
		salary = 0;
		skillSet = "";
	}
	public CandidateBackground(int candidateId) {
		initialize();
		this.candidateId = candidateId;
	}
	public CandidateBackground(int candidateId, String instituteName, String degree, String major) {
		initialize();
		this.setCandidateId(candidateId);
		this.setInfoType("education");
		this.setDegree(degree);
		this.setMajor(major);
		this.setInstituteName(instituteName);
	}
	
	public CandidateBackground(int candidateId, String instituteName, String degree, String major, double cgpa,
			double percentage) {
		initialize();
		this.setCandidateId(candidateId);
		this.setInfoType("education");
		this.setDegree(degree);
		this.setMajor(major);
		this.setInstituteName(instituteName);
		this.setCgpa(cgpa);
		this.setPercentage(percentage);
	}

	public CandidateBackground(int candidateId, String companyName, String jobTitle) {
		initialize();
		this.setInfoType("experience");
		this.setCandidateId(candidateId);
		this.setCompanyName(companyName);
		this.setJobTitle(jobTitle);
	}

	public CandidateBackground(int candidateId, String companyName, String jobTitle, int salary) {
		initialize();
		this.setInfoType("experience");
		this.setCandidateId(candidateId);
		this.setCompanyName(companyName);
		this.setJobTitle(jobTitle);
		this.setSalary(salary);
	}

	public void setAddress(String city, String state, String country) {
		this.setCity(city);
		this.setState(state);
		this.setCountry(country);
	}

	public void setDates(String startDate, String endDate) {
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}

	public int getCandidateId() {
		return candidateId;
	}

	public String getInfoType() {
		return infoType;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getDegree() {
		return degree;
	}

	public String getMajor() {
		return major;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getInstituteName() {
		return instituteName;
	}

	private void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		initialize();
		this.skillSet = skillSet;
		this.infoType = "skills";
	}

	private void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	private void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	private void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	private void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	private void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	private void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	private void setCity(String city) {
		this.city = city;
	}

	private void setState(String state) {
		this.state = state;
	}

	private void setCountry(String country) {
		this.country = country;
	}

	private void setDegree(String degree) {
		this.degree = degree;
	}

	private void setMajor(String major) {
		this.major = major;
	}

}
