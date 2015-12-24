package com.smallow.service.impl;

import com.smallow.dao.UserDao;
import com.smallow.model.User;
import com.smallow.service.UserService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 2015/5/11.
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {


    private UserDao userDao;


    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        this.dao=userDao;

    }
}
