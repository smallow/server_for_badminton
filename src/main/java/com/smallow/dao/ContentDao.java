package com.smallow.dao;

import com.smallow.bean.ContentBean;
import com.smallow.model.sys.Content;
import core.dao.Dao;
import core.support.QueryResult;

/**
 * Created by smallow on 2015/4/29.
 */
public interface ContentDao extends Dao<Content> {

    public QueryResult<ContentBean> doPaginationQuery(ContentBean bean);
    public ContentBean getContentBeanById(Integer contentId);
}
