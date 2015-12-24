package com.hhkj.rmo.dao.impl;

import com.hhkj.rmo.dao.User2Dao;
import com.hhkj.rmo.model.User2;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 2015/6/12.
 */
@Repository
public class User2DaoImpl extends BaseDao<User2> implements User2Dao {
    public User2DaoImpl() {
        super(User2.class);
    }
}
