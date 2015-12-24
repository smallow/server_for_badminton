package com.hhkj.rmo.service.impl;

import com.hhkj.rmo.dao.RolePriDao;
import com.hhkj.rmo.model.RolePri;
import com.hhkj.rmo.service.RolePriService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 2015/6/15.
 */
@Service
public class RolePriServiceImpl extends BaseService<RolePri> implements RolePriService {

    private RolePriDao rolePriDao;

    @Resource
    public void setRolePriDao(RolePriDao rolePriDao) {
        this.rolePriDao = rolePriDao;
        this.dao = rolePriDao;
    }
}
