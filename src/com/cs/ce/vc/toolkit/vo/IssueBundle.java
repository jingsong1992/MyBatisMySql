package com.cs.ce.vc.toolkit.vo;

public class IssueBundle {

	private Integer id;
	private Integer issue_id;
	private String bundle_version;
	private String ishead;
	private String status;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(Integer issue_id) {
		this.issue_id = issue_id;
	}

	public String getBundle_version() {
		return bundle_version;
	}

	public void setBundle_version(String bundle_version) {
		this.bundle_version = bundle_version;
	}

	public String getIshead() {
		return ishead;
	}

	public void setIshead(String ishead) {
		this.ishead = ishead;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
