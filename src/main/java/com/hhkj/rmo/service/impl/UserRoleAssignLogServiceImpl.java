package com.hhkj.rmo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hhkj.rmo.dao.UserRoleAssignLogDao;
import com.hhkj.rmo.model.UserRoleAssignLog;
import com.hhkj.rmo.service.UserRoleAssignLogService;

import core.service.BaseService;

@Service
public class UserRoleAssignLogServiceImpl extends BaseService<UserRoleAssignLog>implements UserRoleAssignLogService {

	private UserRoleAssignLogDao assignLogDao;

	@Resource
	public void setAssignLogDao(UserRoleAssignLogDao assignLogDao) {
		this.assignLogDao = assignLogDao;
		this.dao = assignLogDao;
	}
}
