package com.hhkj.rmo.dao.impl;

import org.springframework.stereotype.Repository;

import com.hhkj.rmo.dao.UserRoleAssignLogDao;
import com.hhkj.rmo.model.UserRoleAssignLog;

import core.dao.BaseDao;
@Repository
public class UserRoleAssignLogDaoImpl extends BaseDao<UserRoleAssignLog> implements UserRoleAssignLogDao {

	public UserRoleAssignLogDaoImpl() {
		super(UserRoleAssignLog.class);
	}
}
