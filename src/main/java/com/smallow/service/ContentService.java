package com.smallow.service;


import com.smallow.bean.ContentBean;
import com.smallow.model.sys.Content;
import core.service.Service;
import core.support.QueryResult;

/**
 * Created by smallow on 2015/4/29.
 */
public interface ContentService extends Service<Content> {


    public QueryResult<ContentBean> doPaginationQuery(ContentBean bean);

    public ContentBean getContentBeanById(Integer contentId);

}
