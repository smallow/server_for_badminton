package com.smallow.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by smallow on 2015/5/11.
 */
@Controller
public class MainController {


    @RequestMapping(value = "/index.htm",method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/login.htm",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
