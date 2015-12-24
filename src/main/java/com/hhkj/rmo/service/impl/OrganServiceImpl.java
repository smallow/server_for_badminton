package com.hhkj.rmo.service.impl;

import com.hhkj.rmo.dao.OrganDao;
import com.hhkj.rmo.model.Organ;
import com.hhkj.rmo.service.OrganService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 2015/6/10.
 */
@Service
public class OrganServiceImpl extends BaseService<Organ> implements OrganService {

    private OrganDao organDao;

    @Resource
    public void setOrganDao(OrganDao organDao) {
        this.organDao = organDao;
        this.dao = organDao;
    }
}
