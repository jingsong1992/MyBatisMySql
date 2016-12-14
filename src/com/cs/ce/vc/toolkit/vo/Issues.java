package com.cs.ce.vc.toolkit.vo;



import java.sql.Timestamp;

public class Issues {

	private Integer id;
	private Integer parent_issue_id;
	private String code;
	private String title;
	private String description;
	private String gdb_code;
	private String rm_code;
	private String type;
	private String report_version;
	private String report_project;
	private Timestamp report_time;
	private String  fixed_version;
	private String status;
	private String fixed_by;
	private Timestamp fixed_time;
	private String sd;
	private String closed_reason;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParent_issue_id() {
		return parent_issue_id;
	}
	public void setParent_issue_id(Integer parent_issue_id) {
		this.parent_issue_id = parent_issue_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGdb_code() {
		return gdb_code;
	}
	public void setGdb_code(String gdb_code) {
		this.gdb_code = gdb_code;
	}
	public String getRm_code() {
		return rm_code;
	}
	public void setRm_code(String rm_code) {
		this.rm_code = rm_code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReport_version() {
		return report_version;
	}
	public void setReport_version(String report_version) {
		this.report_version = report_version;
	}
	public String getReport_project() {
		return report_project;
	}
	public void setReport_project(String report_project) {
		this.report_project = report_project;
	}
	public Timestamp getReport_time() {
		return report_time;
	}
	public void setReport_time(Timestamp report_time) {
		this.report_time = report_time;
	}
	public String getFixed_version() {
		return fixed_version;
	}
	public void setFixed_version(String fixed_version) {
		this.fixed_version = fixed_version;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFixed_by() {
		return fixed_by;
	}
	public void setFixed_by(String fixed_by) {
		this.fixed_by = fixed_by;
	}
	public Timestamp getFixed_time() {
		return fixed_time;
	}
	public void setFixed_time(Timestamp fixed_time) {
		this.fixed_time = fixed_time;
	}
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public String getClosed_reason() {
		return closed_reason;
	}
	public void setClosed_reason(String closed_reason) {
		this.closed_reason = closed_reason;
	}
	

}
