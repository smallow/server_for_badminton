package com.smallow.dao.impl;

import com.smallow.dao.UserDao;
import com.smallow.model.User;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 2015/5/11.
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }
}
