package com.hhkj.rmo.dao.impl;

import com.hhkj.rmo.dao.PriDao;
import com.hhkj.rmo.model.Privilege;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 2015/6/15.
 */
@Repository
public class PriDaoImpl extends BaseDao<Privilege> implements PriDao{
    public PriDaoImpl() {
        super(Privilege.class);
    }
}
