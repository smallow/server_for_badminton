package com.hhkj.rmo.dao;

import com.hhkj.rmo.model.LoginLog;
import core.dao.Dao;
import core.support.QueryResult;

/**
 * Created by smallow on 15/7/5.
 */
public interface LoginLogDao extends Dao<LoginLog> {

    public QueryResult<LoginLog> doPaginationQuery1(LoginLog bean, String loginTime, String loginTimePlus1);
}
