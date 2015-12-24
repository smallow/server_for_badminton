package com.hhkj.rmo.service.impl;

import com.hhkj.rmo.dao.PrivilegeDao;
import com.hhkj.rmo.model.Privilege;
import com.hhkj.rmo.service.PrivilegeService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 2015/6/17.
 */
@Service
public class PrivilegeServiceImpl extends BaseService<Privilege> implements PrivilegeService {

    private PrivilegeDao privilegeDao;

    @Resource
    public void setPrivilegeDao(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
        this.dao=privilegeDao;
    }
}
