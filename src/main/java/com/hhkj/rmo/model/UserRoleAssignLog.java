package com.hhkj.rmo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hhkj.rmo.model.param.UserRoleAssignLogParam;

import core.support.DateTimeSerializer;

@Entity
@Table(name = "rmo_user_role_assign_log")
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions",
		"sortedConditions", "dynamicProperties", "sortColumnsString", "flag" })
public class UserRoleAssignLog extends UserRoleAssignLogParam {

	@Id
	@Column(name = "i_id")
	@GeneratedValue
	private Integer id;

	@Column(name = "s_operate_name")
	private String operateName;

	@Column(name = "d_assign_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date assignTime;

	@Column(name = "s_assign_reason", length = 1000)
	private String assignReason;

	@Column(name = "s_role_code")
	private String roleCode;
	
	@Column(name = "s_role_name")
	private String roleName;

	@Column(name = "i_user_id")
	private Integer userId;
	
	
	@Column(name = "s_user_name")
	private String userName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
	public Date getAssignTime() {
		return assignTime;
	}

	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}

	public String getAssignReason() {
		return assignReason;
	}

	public void setAssignReason(String assignReason) {
		this.assignReason = assignReason;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
