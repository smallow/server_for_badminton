package com.smallow.service.impl;

import com.smallow.bean.ContentBean;
import com.smallow.dao.ContentDao;
import com.smallow.model.sys.Content;
import com.smallow.service.ContentService;
import core.service.BaseService;
import core.support.QueryResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 2015/4/29.
 */
@Service
public class ContentServiceImpl extends BaseService<Content> implements ContentService {


    private ContentDao contentDao;

    @Resource
    public void setContentDao(ContentDao contentDao) {
        this.contentDao = contentDao;
        this.dao=contentDao;
    }


    public QueryResult<ContentBean> doPaginationQuery(ContentBean bean) {
        return contentDao.doPaginationQuery(bean);
    }

    @Cacheable(value="myCache")
    public ContentBean getContentBeanById(Integer contentId) {
        return contentDao.getContentBeanById(contentId);
    }
}
