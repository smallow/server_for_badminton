package com.hhkj.rmo.service.impl;

import com.hhkj.rmo.dao.RoleDao;
import com.hhkj.rmo.model.Role;
import com.hhkj.rmo.service.RoleService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 15/6/14.
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    private RoleDao roleDao;

    @Resource
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
        this.dao=roleDao;
    }
}
