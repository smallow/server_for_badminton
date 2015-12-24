package com.smallow.controller.admin;

import com.alibaba.fastjson.JSON;
import com.hhkj.rmo.model.Privilege;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class HelloController {
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	public String printWelcome(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		model.addAttribute("message", "Hello world!");
		List<Privilege> privilegeList=(List<Privilege>)request.getSession().getAttribute("userPri");
		List<Map<String,Object>> businessModule=new ArrayList<Map<String, Object>>();
		if(privilegeList!=null && privilegeList.size()>0){
			Map<String,Object> map=null;
			for(Privilege privilege:privilegeList){
				map=new HashMap<String, Object>();
				map.put("name", privilege.getBusinessModuleName());
				map.put("code", privilege.getBusinessModuleCode());
				if(checkBusinessModule(privilege.getBusinessModuleCode(),businessModule)){
					businessModule.add(map);
				}
			}
		}
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("businessModule",businessModule);
		map.put("privilegeList",privilegeList);
		String priJson= JSON.toJSONString(map);
		//System.out.println(priJson);
		model.addAttribute("priJson",priJson);
		model.addAttribute("loginName",request.getSession().getAttribute("employeeName"));
		model.addAttribute("loginTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

		return "hello1";
	}

	private boolean checkBusinessModule(String businessCode, List<Map<String,Object>> businessModule){
		for(Map<String,Object> map:businessModule){
			if(((String)map.get("code")).equals(businessCode)){
				return false;
			}
		}
		return true;
	}


}