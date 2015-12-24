package com.smallow.controller.monile;

import com.smallow.core.JavaEEFrameworkBaseController;
import com.smallow.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by smallow on 15/11/29.
 */
@Controller
@RequestMapping("/badminton/member")
public class MemberManageController extends JavaEEFrameworkBaseController<Member> {


    /*@RequestMapping("/manage")
    public String manage() {
        return "badminton/member/manage";
    }*/


    @RequestMapping("/manage.listRegistration")
    public String list(HttpServletRequest request) {

        return "";
    }



}
