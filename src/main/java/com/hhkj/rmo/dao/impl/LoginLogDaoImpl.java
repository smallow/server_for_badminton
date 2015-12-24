package com.hhkj.rmo.dao.impl;


import com.hhkj.rmo.dao.LoginLogDao;
import com.hhkj.rmo.model.LoginLog;
import core.dao.BaseDao;
import core.support.QueryResult;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by smallow on 15/7/5.
 */
@Repository
public class LoginLogDaoImpl extends BaseDao<LoginLog> implements LoginLogDao {

    public LoginLogDaoImpl() {
        super(LoginLog.class);
    }

    @Override
    public QueryResult<LoginLog> doPaginationQuery1(LoginLog bean,String loginTime,String loginTimePlus1) {
        Integer firstResult=bean.getFirstResult();
        Integer maxResult=bean.getMaxResults();
        Long totalCount=0l;
        QueryResult<LoginLog> queryResult=new QueryResult<LoginLog>();
        StringBuffer sql=new StringBuffer("select * from sys_loginlog s where 1=1 ");
        StringBuffer countSql=new StringBuffer("select count(i_id) as num from sys_loginlog s where 1=1 ");
        if(loginTime!=null){
            sql.append(" and s.d_login_time between '").append(loginTime).append("' and '").append(loginTimePlus1).append("'");
            countSql.append(" and s.d_login_time between '").append(loginTime).append("' and '").append(loginTimePlus1).append("'");
        }
        Query q=getSession().createSQLQuery(countSql.toString());
        List<Object> list=q.list();
        if(list!=null && list.size()>0){
            Object obj=list.get(0);
            totalCount=(Long.valueOf(String.valueOf(obj)));
            queryResult.setTotalCount(totalCount);
        }

        sql.append(" order by s.d_login_time desc limit ").append(firstResult).append(",").append(maxResult);
        q=getSession().createSQLQuery(sql.toString());
        List<Object[]> __list=new ArrayList<Object[]>();
        __list =q.list();
        List<LoginLog> _list=new ArrayList<LoginLog>();
        if(__list!=null && __list.size()>0){
            LoginLog _bean=null;
            for(Object [] obj:__list){
                _bean=new LoginLog();
                _bean.setId(Integer.parseInt(String.valueOf(obj[0])));
                _bean.setLoginCode(String.valueOf(obj[1]));
                _bean.setLoginName(String.valueOf(obj[2]));
                _bean.setLoginTime((Date) obj[3]);
                _bean.setLogoutTime((Date)obj[4]);
                _list.add(_bean);
            }
        }
        queryResult.setResultList(_list);
        return queryResult;
    }
}
