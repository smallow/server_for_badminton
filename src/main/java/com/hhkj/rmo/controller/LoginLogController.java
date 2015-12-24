package com.hhkj.rmo.controller;


import com.hhkj.rmo.model.LoginLog;
import com.hhkj.rmo.service.LoginLogService;
import com.smallow.core.JavaEEFrameworkBaseController;
import core.support.JqGridPageView;
import core.support.QueryResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by smallow on 15/7/5.
 */
@Controller
@RequestMapping("/sys/log")
public class LoginLogController extends JavaEEFrameworkBaseController<LoginLog> {
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    @Resource
    private LoginLogService loginLogService;

    @RequestMapping("/manage")
    public String manage() {
        return "sys/log/list";
    }


    @RequestMapping(value = "/getLoginLogs", method = {RequestMethod.GET, RequestMethod.POST})
    public void getLoginLogs(HttpServletRequest request, HttpServletResponse response,String date) throws IOException, ParseException {
        Integer firstResult = Integer.valueOf(request.getParameter("page"));
        Integer pageSize = Integer.valueOf(request.getParameter("rows"));
        LoginLog entity=new LoginLog();
        entity.setFirstResult((firstResult - 1) * pageSize);
        entity.setMaxResults(pageSize);
        Map<String,String> map=new HashMap<String, String>();
        map.put("loginTime","desc");
        entity.setSortedConditions(map);
        QueryResult<LoginLog> queryResult=null;
        if(date!=null){
            Calendar now = Calendar.getInstance();
            now.setTime(format.parse(date));
            now.add(Calendar.DAY_OF_MONTH, 1);
            Date plusDay=now.getTime();
            String datePlus=format.format(plusDay);
            queryResult=loginLogService.doPaginationQuery1(entity,date,datePlus);
        }else{
            queryResult=loginLogService.doPaginationQuery(entity);
        }


        JqGridPageView jqGridPageView=new JqGridPageView();
        jqGridPageView.setRows(queryResult.getResultList());
        jqGridPageView.setTotal(queryResult.getTotalCount());
        writeJSON(response, jqGridPageView);
    }

    @RequestMapping(value = "/delLoginLogs", method = {RequestMethod.GET, RequestMethod.POST})
    public void  delLoginLogsByIds(String ids,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,String> msg=new HashMap<String,String>();
        try{
            if(ids!=null&& !"".equals(ids)){
                for(String id:ids.split(",")){
                    loginLogService.deleteByPK(Integer.parseInt(id));
                }
            }
            msg.put("msg", "success");
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","failed");
        }
        writeJSON(response,msg);
    }
}
