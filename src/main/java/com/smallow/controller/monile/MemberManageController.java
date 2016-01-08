package com.smallow.controller.monile;

import com.alibaba.fastjson.JSON;
import com.smallow.bean.ApiResponse;
import com.smallow.core.JavaEEFrameworkBaseController;
import com.smallow.model.Member;
import com.smallow.service.MemberService;
import core.util.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

/**
 * Created by smallow on 15/11/29.
 */
@Controller
@RequestMapping("/member")
public class MemberManageController {


    @Resource
    private MemberService memberService;

    @RequestMapping(value = "/memberLogin.do", method = {RequestMethod.POST, RequestMethod.GET})
    public void getTodayActivityRecord(HttpServletRequest request, HttpServletResponse response, String mobile, String pwd) throws ParseException {
        Member member = memberService.getByProerties(new String[]{"mobile", "pwd"}, new String[]{mobile, pwd});
        ApiResponse<Member> apiResponse=null;
        if(member!=null){
            apiResponse=new ApiResponse<Member>("0","登陆成功");
            apiResponse.setObj(member);

        }else{
            apiResponse=new ApiResponse<Member>("1","登陆失败,请检查手机号密码是否填写正确!");
        }

        String json= JSON.toJSONString(apiResponse);
        ResponseUtils.renderJson(response,json);

    }


}
