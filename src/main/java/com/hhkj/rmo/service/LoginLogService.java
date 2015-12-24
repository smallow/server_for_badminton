package com.hhkj.rmo.service;


import com.hhkj.rmo.model.LoginLog;
import core.service.Service;
import core.support.QueryResult;

/**
 * Created by smallow on 15/7/5.
 */
public interface LoginLogService extends Service<LoginLog> {
    public QueryResult<LoginLog> doPaginationQuery1(LoginLog bean, String loginTime, String loginTimePlus1);
}
