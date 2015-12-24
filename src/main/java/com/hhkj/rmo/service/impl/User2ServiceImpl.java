package com.hhkj.rmo.service.impl;

import com.hhkj.rmo.dao.User2Dao;
import com.hhkj.rmo.model.User2;
import com.hhkj.rmo.service.User2Service;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by smallow on 2015/6/12.
 */
@Service
public class User2ServiceImpl extends BaseService<User2> implements User2Service {


    private User2Dao user2Dao;

    @Resource
    public void setUser2Dao(User2Dao user2Dao) {
        this.user2Dao = user2Dao;
        this.dao = user2Dao;
    }

    @Override
    public boolean deleteByPK(Serializable... id) {
        return super.deleteByPK(id);
    }
}

