package com.hhkj.rmo.dao.impl;

import com.hhkj.rmo.dao.PrivilegeDao;
import com.hhkj.rmo.model.Privilege;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 2015/6/17.
 */
@Repository
public class PrivilegeDaoImpl extends BaseDao<Privilege> implements PrivilegeDao {

    public PrivilegeDaoImpl() {
        super(Privilege.class);
    }
}
