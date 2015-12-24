package com.hhkj.rmo.controller;

import com.alibaba.fastjson.JSON;
import com.hhkj.rmo.bean.RoleBean;
import com.hhkj.rmo.model.*;
import com.hhkj.rmo.service.*;
import com.smallow.core.JavaEEFrameworkBaseController;
import core.support.JqGridPageView;
import core.support.JqueryBaseParameter;
import core.support.QueryResult;
import core.util.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by smallow on 15/6/14.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends JavaEEFrameworkBaseController<RoleBean> {

    @Resource
    private RoleService roleService;

    @Resource
    private RolePriService rolePriService;

    @Resource
    private PriService priService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private User2Service user2Service;

    @RequestMapping("/manage")
    public String manage() {
        return "rmo/role/list";
    }

    @RequestMapping(value = "/roleList", method = {RequestMethod.GET, RequestMethod.POST})
    public void list(HttpServletRequest request, HttpServletResponse response, Integer organId) throws IOException {

        Integer firstResult = Integer.valueOf(request.getParameter("page"));
        Integer pageSize = Integer.valueOf(request.getParameter("rows"));
        Role role = new Role();
        role.setFirstResult((firstResult - 1) * pageSize);
        role.setMaxResults(pageSize);

        QueryResult<Role> roleQueryResult = roleService.doPaginationQuery(role);
        List<Role> roleList = roleQueryResult.getResultList();
        List<RoleBean> list = new ArrayList<RoleBean>();
        if (roleList != null && roleList.size() > 0) {
            RoleBean bean = null;
            for (Role role1 : roleList) {
                bean = new RoleBean();
                bean.setId(role1.getId());
                bean.setRoleName(role1.getRoleName());
                bean.setRoleCode(role1.getRoleCode());
                bean.setDescription(role1.getDescription());
                //bean.setOper("<a href=\"javascript:void(0)\" class=\"easyui-linkbutton c4\"  onclick=\"\">分配权限</a>");
                list.add(bean);
            }
        }
        JqGridPageView<RoleBean> jqGridPageView = new JqGridPageView<RoleBean>();
        jqGridPageView.setRows(list);
        jqGridPageView.setTotal(list.size());
        writeJSON(response, jqGridPageView);
    }

    @RequestMapping(value = "/priTree", method = {RequestMethod.GET, RequestMethod.POST})
    public void getOrgTree(HttpServletRequest request, HttpServletResponse response, String roleCode) throws IOException {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        if (roleCode != null) {
            List<Privilege> _list = new ArrayList<Privilege>();
            _list=priService.doQueryAll();

            if (_list.size() > 0) {
                Map<String, Object> map = null;
                Map<String,String> attr=null;
                for (Privilege privilege : _list) {
                    if (privilege.getParentPriCode().equals(privilege.getBusinessModuleCode())) {
                        if(!checkRootNode(privilege,data)){
                            map = new HashMap<String, Object>();
                            map.put("id", privilege.getBusinessModuleCode());
                            map.put("text", privilege.getBusinessModuleName());
                            attr=new HashMap<String, String>();
                            attr.put("code",privilege.getBusinessModuleCode());
                            attr.put("parentCode", privilege.getBusinessModuleCode());
                            map.put("attributes", attr);
                            Privilege privilege1=new Privilege();
                            privilege1.setId(Integer.parseInt(privilege.getBusinessModuleCode()));
                            privilege1.setPriCode(privilege.getBusinessModuleCode());
                            privilege1.setPriName(privilege.getBusinessModuleName());
                            recursion(privilege1, map, _list);
                            data.add(map);
                        }
                    }
                }
            }

            List<RolePri> list = rolePriService.queryByProerties("roleCode", roleCode);
            if (list != null && list.size() > 0) {
                for (RolePri rolePri : list) {
                    Privilege obj = priService.getByProerties("priCode", rolePri.getPriCode());
                    if(obj!=null){
                        checkedNode(data,obj);
                    }

                }
            }
        }
        writeJSON(response, data);
    }




    @RequestMapping(value = "/saveRolePri",method = {RequestMethod.GET, RequestMethod.POST})
    public void saveRolePri(HttpServletRequest request, HttpServletResponse response, String roleCode,String priCodes){
        Map<String,String> map=new HashMap<String, String>();
        if(roleCode!=null && priCodes!=null){
            try{
                String []priCode=priCodes.split(",");
                if(priCode.length>0){
                    rolePriService.deleteByProperties("roleCode",roleCode);
                    RolePri obj=null;
                    for(String _priCode:priCode){
                        obj=new RolePri();
                        obj.setRoleCode(roleCode);
                        obj.setPriCode(_priCode);
                        rolePriService.persist(obj);
                    }
                    map.put("msg","success");
                }
            }catch (Exception e){
                e.printStackTrace();
                map.put("msg","failed");
            }
        }else{
            map.put("msg","param valid");
        }

        String json= JSON.toJSONString(map);
        ResponseUtils.renderJson(response, json);
    }


    @RequestMapping(value = "/saveRole",method = {RequestMethod.GET, RequestMethod.POST})
    public void saveRole(HttpServletRequest request, HttpServletResponse response,Role entity) throws IOException {

        JqueryBaseParameter jqueryBaseParameter=(JqueryBaseParameter)entity;

        try{
            if(CMD_EDIT.equals(jqueryBaseParameter.getCmd())){
                Role role=roleService.get(entity.getId());
                role.setRoleName(entity.getRoleName());
                role.setDescription(entity.getDescription());
                roleService.update(role);
            }else if(CMD_NEW.equals(jqueryBaseParameter.getCmd())){
                roleService.persist(entity);
            }
            jqueryBaseParameter.setCmd(CMD_EDIT);
            jqueryBaseParameter.setSuccess(true);
        }catch(Exception e){
            e.printStackTrace();
        }

        writeJSON(response, jqueryBaseParameter);
    }


    @RequestMapping(value = "/delRole",method = {RequestMethod.GET, RequestMethod.POST})
    public void delEmployee(HttpServletRequest request, HttpServletResponse response,Integer id,String roleCode) throws IOException {

        Map<String,Object> msg=new HashMap<String,Object>();
        try{
            /*if(ids!=null&& !"".equals(ids)){
                for(String id:ids.split(",")){
                    employeeService.deleteByPK(Integer.valueOf(id));
                }
            }*/
            boolean b=roleService.deleteByPK(id);
            if(roleCode!=null){
                rolePriService.deleteByProperties("roleCode",roleCode);
            }
            if(b){
                msg.put("success",true);
            }else{
                msg.put("success",false);
            }

        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg", "failed");
        }
        writeJSON(response,msg);
    }


    @RequestMapping(value = "/getRoleByUserId",method = {RequestMethod.GET, RequestMethod.POST})
    public void getRoleByUserId(HttpServletRequest request, HttpServletResponse response,Integer employeeId) throws IOException {

        Map<String,Object> data=new HashMap<String,Object>();
        List<Role> roleList=roleService.doQueryAll();
        User2 user2=user2Service.getByProerties("employeeId", employeeId);
        if(user2!=null){
            List<UserRole> userRoleList=userRoleService.queryByProerties("userId",user2.getId());
            data.put("allRoles",roleList);
            data.put("userRoles",userRoleList);
            data.put("msg","success");
        }else{
            data.put("allRoles",roleList);
            data.put("userRoles",new ArrayList<UserRole>());
            data.put("msg","faile");
        }

        writeJSON(response, data);
    }













    private void recursion(Privilege privilege, Map<String, Object> map, List<Privilege> list) {
        List<Privilege> _chidrenList = new ArrayList<Privilege>();
        _chidrenList = findChildrenNode(privilege, list);
        List<Map<String, Object>> __childrenList = new ArrayList<Map<String, Object>>();
        if (_chidrenList.size() > 0) {
            Map<String, Object> map1 = null;
            Map<String,String> attr=null;
            for (Privilege privilege1 : _chidrenList) {
                map1 = new HashMap<String, Object>();
                map1.put("id", privilege1.getId());
                map1.put("text", privilege1.getPriName());
                attr=new HashMap<String, String>();
                attr.put("code",privilege1.getPriCode());
                attr.put("parentCode",privilege1.getParentPriCode());
                map1.put("attributes",attr);
                __childrenList.add(map1);
                recursion(privilege1, map1, list);
            }
            map.put("children", __childrenList);
        }
    }

    private List<Privilege> findChildrenNode(Privilege organ, List<Privilege> totalData) {
        List<Privilege> list = new ArrayList<Privilege>();
        for (Privilege organ1 : totalData) {
            if (organ1.getParentPriCode().equals(organ.getPriCode()))
                list.add(organ1);
        }
        return list;
    }


    private void checkedNode(List<Map<String, Object>> data,Privilege node){
        Map<String,String> attr=new HashMap<String, String>();
        Map<String,String> _attr=new HashMap<String, String>();
        String priCode=null;
        String parentPriCode=null;
        String _priCode=null;
        boolean b=false;
        if(data!=null && data.size()>0){
            for(Map<String,Object> map:data){
                attr=(Map<String,String>)map.get("attributes");
                priCode=attr.get("code");
                parentPriCode=attr.get("parentCode");
                if(node.getParentPriCode().equals(priCode)){
                    List<Map<String, Object>> chiildren=(List<Map<String, Object>>)map.get("children");
                    if(chiildren!=null &&chiildren.size()>0){
                        for(Map<String,Object> _map:chiildren){
                            _attr=(Map<String,String>)_map.get("attributes");
                            _priCode=(String)_attr.get("code");
                            if(_priCode.equals(node.getPriCode())){
                                _map.put("checked",true);
                                b=true;
                                break;
                            }
                        }
                    }
                }
                if(b){
                    break;
                }
            }
            if(!b){
                for(Map<String,Object> map:data){
                    checkedNode((List<Map<String, Object>>)map.get("children"),node);
                }
            }
        }


    }

    private boolean checkRootNode(Privilege privilege,List<Map<String,Object>> list){

        if(list!=null && list.size()>0){
            String businessModuleCode="";
            for(Map<String,Object> _map:list){
                businessModuleCode=(String)((Map<String,Object>)_map.get("attributes")).get("code");
                if(privilege.getBusinessModuleCode().equals(businessModuleCode)){
                    return true;
                }
            }
        }
        return false;
    }

}
