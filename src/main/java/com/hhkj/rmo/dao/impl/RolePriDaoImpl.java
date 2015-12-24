package com.hhkj.rmo.dao.impl;

import com.hhkj.rmo.dao.RolePriDao;
import com.hhkj.rmo.model.RolePri;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 2015/6/15.
 */
@Repository
public class RolePriDaoImpl extends BaseDao<RolePri> implements RolePriDao {
    public RolePriDaoImpl() {
        super(RolePri.class);
    }
}
