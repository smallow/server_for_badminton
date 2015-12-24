package com.hhkj.rmo.dao.impl;

import com.hhkj.rmo.dao.OrganDao;
import com.hhkj.rmo.model.Organ;
import com.smallow.dao.UserDao;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 2015/6/10.
 */
@Repository
public class OrganDaoImpl extends BaseDao<Organ> implements OrganDao {

    public OrganDaoImpl() {
        super(Organ.class);
    }
}
