package com.smallow.controller.monile;

import com.alibaba.fastjson.JSON;
import com.smallow.bean.ContentBean;

import com.smallow.core.JavaEEFrameworkBaseController;
import com.smallow.service.ContentService;
import core.support.QueryResult;
import core.util.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by smallow on 2015/5/21.
 */
@Controller
@RequestMapping("/mobile")
public class ContentForMobileController extends JavaEEFrameworkBaseController<ContentBean> {

    @Resource
    private ContentService contentService;

    @RequestMapping(value = "/getMerchantContent.do",method = {RequestMethod.GET})
    public void getMerchantContent(HttpServletRequest request,HttpServletResponse response,Integer page,Integer pageSize) throws IOException {
        Integer firstResult = page;
        ContentBean content=new ContentBean();
        content.setFirstResult((firstResult - 1) * pageSize);
        content.setMaxResults(pageSize);
        QueryResult<ContentBean> contentQueryResult=contentService.doPaginationQuery(content);
        List<ContentBean> list=contentQueryResult.getResultList();
        writeJSON(response, list);
    }

    @RequestMapping(value = "/getContentById.do")
    public void getContentById(HttpServletRequest request,HttpServletResponse response,Integer contentId) throws IOException {
        ContentBean bean=contentService.getContentBeanById(contentId);

        if(bean!=null){
            writeJSON(response, bean);
        }

    }

    @RequestMapping(value = "/findeContent/{contentId}.do",method = {RequestMethod.GET})
    public void findContentById(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer contentId) throws IOException {
        ContentBean bean=contentService.getContentBeanById(contentId);
        if(bean!=null){
            writeJSON(response, bean);
        }
    }

}
