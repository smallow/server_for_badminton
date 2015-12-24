package com.hhkj.rmo.service.impl;


import com.hhkj.rmo.dao.LoginLogDao;
import com.hhkj.rmo.model.LoginLog;
import com.hhkj.rmo.service.LoginLogService;
import core.service.BaseService;
import core.support.QueryResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 15/7/5.
 */
@Service
public class LoginLogServiceImpl extends BaseService<LoginLog> implements LoginLogService {


    private LoginLogDao loginLogDao;

    @Resource
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
        this.dao=loginLogDao;
    }

    @Override
    public QueryResult<LoginLog> doPaginationQuery1(LoginLog bean,String loginTime,String loginTimePlus1) {
        return loginLogDao.doPaginationQuery1(bean,loginTime,loginTimePlus1);
    }
}
