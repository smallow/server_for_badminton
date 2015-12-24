package com.smallow.controller.front;

import com.alibaba.fastjson.JSON;
import com.smallow.model.User;
import com.smallow.service.UserService;
import core.util.MD5;
import core.util.ResponseUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by smallow on 2015/5/11.
 */
@Controller
@RequestMapping("/frontuser")
public class UserFrontController {

    @Resource
    private UserService userService;






    @RequestMapping(value = "/login.htm")
    public void login(HttpServletRequest request,HttpServletResponse response,String telephone,String pwd){
        Map<String,Object> map=new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(telephone)  && StringUtils.isNotEmpty(pwd)){
           User user= userService.getByProerties(new String[]{"telephone", "pwd"}, new String[]{telephone, MD5.crypt(pwd)});
            if(user!=null){
                map.put("msg","success");
                map.put("userId",user.getId());
                request.getSession().setAttribute("userId",user.getId());
            }else{
                map.put("msg","failed");
            }
        }

        String json= JSON.toJSONString(map);
        ResponseUtils.renderJson(response,json);


    }

}
