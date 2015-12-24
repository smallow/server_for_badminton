package com.hhkj.rmo.service.impl;

import com.hhkj.rmo.dao.UserRoleDao;
import com.hhkj.rmo.model.UserRole;
import com.hhkj.rmo.service.UserRoleService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 2015/6/16.
 */
@Service
public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService {
    private UserRoleDao userRoleDao;

    @Resource
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
        this.dao = userRoleDao;
    }
}
