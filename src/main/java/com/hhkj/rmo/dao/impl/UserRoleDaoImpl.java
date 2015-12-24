package com.hhkj.rmo.dao.impl;

import com.hhkj.rmo.dao.UserRoleDao;
import com.hhkj.rmo.model.UserRole;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 2015/6/16.
 */
@Repository
public class UserRoleDaoImpl extends BaseDao<UserRole> implements UserRoleDao {
    public UserRoleDaoImpl() {
        super(UserRole.class);
    }
}
