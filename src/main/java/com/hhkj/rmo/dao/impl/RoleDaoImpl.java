package com.hhkj.rmo.dao.impl;

import com.hhkj.rmo.dao.RoleDao;
import com.hhkj.rmo.model.Role;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 15/6/14.
 */
@Repository
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {
    public RoleDaoImpl() {
        super(Role.class);
    }
}
