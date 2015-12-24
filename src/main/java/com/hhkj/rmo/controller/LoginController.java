package com.hhkj.rmo.controller;

import com.alibaba.fastjson.JSON;
import com.hhkj.rmo.model.*;
import com.hhkj.rmo.service.*;



import core.util.MD5;
import core.util.ResponseUtils;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by smallow on 2015/6/17.
 * 用户登录处理
 */


@Controller
public class LoginController {

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private User2Service user2Service;

    @Resource
    private RolePriService rolePriService;

    @Resource
    private PrivilegeService privilegeService;

    @Resource
    private LoginLogService loginLogService;

    //@Resource
    //private EmpInfoService empInfoService;

    @RequestMapping(value = "/login", method ={RequestMethod.POST} )
    public void login(HttpServletResponse response,HttpServletRequest request,String loginCode,String pwd) {
        Map<String,String> map=new HashMap<String,String>();
        User2 user=user2Service.getByProerties(new String[]{"loginCode","pwd"},new String[]{loginCode, MD5.crypt(pwd)});
        if(user!=null){
            List<Privilege> userPrivilege=new ArrayList<Privilege>();
            /**
             * 根据登录操作员ID获取操作员岗位
             */
            List<UserRole> userRoles=userRoleService.queryByProerties("userId",user.getId());
            if(userRoles!=null && userRoles.size()>0){
                for(UserRole userRole:userRoles){
                    /**
                     * 根据岗位找权限
                     */
                    List<RolePri> _list=rolePriService.queryByProerties("roleCode",userRole.getRoleCode());
                    /**
                     * 把权限加到集合当中放入session
                     */
                    priCheck(userPrivilege,_list);
                }

                LoginLog loginLog=new LoginLog();
                loginLog.setLoginCode(user.getLoginCode());
               // String loginUserName=empInfoService.getByProerties("empInfoId",Long.valueOf(String.valueOf(user.getEmployeeId()))).getName();
                loginLog.setLoginName("");
                loginLog.setLoginTime(new Date());
                loginLogService.persist(loginLog);
                request.getSession().setAttribute("userPri",userPrivilege);
                request.getSession().setAttribute("userId",user.getId());
                request.getSession().setAttribute("loginCode",user.getLoginCode());
                request.getSession().setAttribute("employeeId",user.getEmployeeId());
                request.getSession().setAttribute("employeeName","");

            }
            map.put("msg","success");
        }else{
            map.put("msg", "faile");
        }


        String json= JSON.toJSONString(map);
        ResponseUtils.renderJson(response,json);
    }

    /**
     * 将获取到的权限加入到集合中
     * @param userPrivilege  权限集合,需要放入session
     * @param _list 当前岗位对应的权限集合
     */
    private void priCheck(List<Privilege> userPrivilege,List<RolePri> _list){
        Map<String,Object> totalPriCache=new HashMap<String, Object>();
        if(_list!=null &&_list.size()>0){
            Privilege privilege=null;
            List<Privilege> totalPri=null;//各个对应的业务环节下的权限总集合,为了查找父节点用
            for(RolePri rolePri:_list){
                privilege=privilegeService.getByProerties("priCode",rolePri.getPriCode());
                if(!totalPriCache.containsKey(privilege.getBusinessModuleCode())){
                    totalPri=privilegeService.queryByProerties("businessModuleCode", privilege.getBusinessModuleCode());
                    totalPriCache.put(privilege.getBusinessModuleCode(),totalPri);
                }else{
                    totalPri=(List<Privilege>)totalPriCache.get(privilege.getBusinessModuleCode());
                }

                List<Privilege> parentPri=new ArrayList<Privilege>();
                /**
                 * 查找当前权限节点的父亲节点，一直到顶层，为了构建完整的树结构
                 */
                findParent(privilege,parentPri,totalPri);
                if(!userPrivilege.contains(privilege)){
                    userPrivilege.add(privilege);
                }
                if(parentPri.size()>0){
                    for(Privilege privilege1:parentPri){
                        if(!userPrivilege.contains(privilege1)){
                            userPrivilege.add(privilege1);
                        }
                    }
                }

            }
        }
    }

    /**
     * 递归查找权限的父亲节点
     * @param privilege
     * @param parentPri
     * @param totalPri
     */
    private void findParent(Privilege privilege,List<Privilege> parentPri,List<Privilege> totalPri){
        for(Privilege privilege1:totalPri){
            if(privilege.getParentPriCode().equals(privilege1.getPriCode())){
                if(!parentPri.contains(privilege1)){
                    parentPri.add(privilege1);
                }
                /**
                 * 如果当前节点的父亲节点是业务类型代码，说明是大业务模块里的最顶层,不是就需要递归下去找
                 */
                if(!privilege1.getParentPriCode().equals(privilege1.getBusinessModuleCode())){
                    findParent(privilege1,parentPri,totalPri);
                }
                break;
            }
        }

    }

}
