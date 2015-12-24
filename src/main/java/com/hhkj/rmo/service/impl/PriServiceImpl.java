package com.hhkj.rmo.service.impl;

import com.hhkj.rmo.dao.PriDao;
import com.hhkj.rmo.model.Privilege;
import com.hhkj.rmo.service.PriService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 2015/6/15.
 */
@Service
public class PriServiceImpl extends BaseService<Privilege> implements PriService {

    private PriDao priDao;

    @Resource
    public void setPriDao(PriDao priDao) {
        this.priDao = priDao;
        this.dao = priDao;
    }
}
