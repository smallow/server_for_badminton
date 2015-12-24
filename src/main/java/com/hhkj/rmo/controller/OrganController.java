package com.hhkj.rmo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hhkj.rmo.model.Employee;
import com.hhkj.rmo.model.Organ;
import com.hhkj.rmo.model.User2;
import com.hhkj.rmo.model.UserRole;
import com.hhkj.rmo.model.UserRoleAssignLog;
import com.hhkj.rmo.service.EmployeeService;
import com.hhkj.rmo.service.OrganService;
import com.hhkj.rmo.service.RoleService;
import com.hhkj.rmo.service.User2Service;
import com.hhkj.rmo.service.UserRoleAssignLogService;
import com.hhkj.rmo.service.UserRoleService;

import com.smallow.core.JavaEEFrameworkBaseController;

import core.support.JqGridPageView;
import core.support.JqueryBaseParameter;
import core.support.QueryResult;
import core.util.MD5;

/**
 * Created by smallow on 2015/6/10.
 */
@Controller
@RequestMapping("/organ")
public class OrganController extends JavaEEFrameworkBaseController<Organ> {

    @Resource
    private OrganService organService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private User2Service user2Service;

    @Resource
    private UserRoleService userRoleService;
    //@Resource
    //private EmpInfoService empInfoService;

    @Resource
    private  UserRoleAssignLogService userRoleAssignLogService;
    
    @Resource
    private RoleService roleService;
    
    @RequestMapping("/manage")
    public String manage() {
        return "rmo/organ/list";
    }

    @RequestMapping(value = "/orgTree", method = {RequestMethod.GET, RequestMethod.POST})
    public void getOrgTree(HttpServletRequest request, HttpServletResponse response, Integer currentUserId) throws IOException {

        /*Integer firstResult = Integer.valueOf(request.getParameter("page"));
        Integer pageSize = Integer.valueOf(request.getParameter("rows"));*/
        //Organ entity = new Organ();
        // entity.set$eq_orgCode("root");
        //QueryResult<Organ> queryResult = organService.doQueryAll();
        /*JqGridPageView<Organ> organJqGridPageView=new JqGridPageView<Organ>();
        organJqGridPageView.setRows(queryResult.getResultList());
        organJqGridPageView.setTotal(queryResult.getTotalCount());
        writeJSON(response,organJqGridPageView);*/
        List<Organ> list = organService.doQueryAll();
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        if (list != null && list.size() > 0) {
            Map<String, Object> map = null;
            for (Organ organ : list) {
                if (organ.getOrgCode().equals("root")) {
                    map = new HashMap<String, Object>();
                    map.put("id", organ.getId());
                    map.put("text", organ.getOrgName());
                    recursion(organ, map, list);
                    data.add(map);
                }

            }
        }
        /*String json = JSON.toJSONString(data);
        ResponseUtils.renderJson(response, json);*/
        writeJSON(response, data);
    }

    @RequestMapping(value = "/getOrgan",method = {RequestMethod.GET, RequestMethod.POST})
    public void getOrganInfo(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException {
        //Organ organ=new Organ();
        //organ.set$eq_id(id);
        Map<String, Object> map = new HashMap<String, Object>();
        Organ organ = organService.get(id);
        Organ parentOrgan=organService.getByProerties("orgCode",organ.getParentCode());
        map.put("entity", organ);
        map.put("parent", parentOrgan);
        writeJSON(response, map);
    }
    @RequestMapping(value = "/getEmployee",method = {RequestMethod.GET, RequestMethod.POST})
    public void getEmployee(HttpServletRequest request, HttpServletResponse response, Integer organId,String organName) throws IOException {
        if(organId!=null){
            Integer firstResult = Integer.valueOf(request.getParameter("page"));
            Integer pageSize = Integer.valueOf(request.getParameter("rows"));
            Employee employee=new Employee();
            employee.set$eq_orgId(organId);
            employee.setFirstResult((firstResult - 1) * pageSize);
            employee.setMaxResults(pageSize);
            QueryResult<Employee> queryResult=employeeService.doPaginationQuery(employee);
            JqGridPageView jqGridPageView=new JqGridPageView();
            jqGridPageView.setRows(queryResult.getResultList());
            jqGridPageView.setTotal(queryResult.getTotalCount());
            writeJSON(response,jqGridPageView);


           /* Integer firstResult = Integer.valueOf(request.getParameter("page"));
            Integer pageSize = Integer.valueOf(request.getParameter("rows"));
            EmpInfo employee=new EmpInfo();
            employee.setFirstResult((firstResult - 1) * pageSize);
            employee.setMaxResults(pageSize);
            employee.set$eq_department(organName);
            QueryResult<EmpInfo> queryResult=empInfoService.doPaginationQuery(employee);
            JqGridPageView jqGridPageView=new JqGridPageView();
            jqGridPageView.setRows(queryResult.getResultList());
            jqGridPageView.setTotal(queryResult.getTotalCount());
            writeJSON(response,jqGridPageView);*/



        }
    }

    @RequestMapping(value = "/saveOrgan",method = {RequestMethod.GET, RequestMethod.POST})
    public void saveOrgan(HttpServletRequest request, HttpServletResponse response,Organ entity) throws IOException {

        JqueryBaseParameter jqueryBaseParameter=(JqueryBaseParameter)entity;

            try{
                if(CMD_EDIT.equals(jqueryBaseParameter.getCmd())){
                    Organ organ=organService.get(entity.getId());
                    organ.setOrgName(entity.getOrgName());
                    organ.setOrgShortName(entity.getOrgShortName());
                    organ.setOrgDesc(entity.getOrgDesc());
                    organService.update(organ);
                }else if(CMD_NEW.equals(jqueryBaseParameter.getCmd())){
                    entity.setCreateTime(new Date());
                    organService.persist(entity);
                }
                jqueryBaseParameter.setCmd(CMD_EDIT);
                jqueryBaseParameter.setSuccess(true);
            }catch(Exception e){
                e.printStackTrace();
            }

        writeJSON(response,jqueryBaseParameter);
    }

    @RequestMapping(value = "/editOrgan",method = {RequestMethod.GET, RequestMethod.POST})
    public void editOrgan(HttpServletRequest request, HttpServletResponse response,Integer id) throws IOException {

        if(id!=null){
            Organ organ=organService.get(id);
            writeJSON(response,organ);
        }
    }

    @RequestMapping(value = "/delOrgan",method = {RequestMethod.GET, RequestMethod.POST})
    public void delOrgan(HttpServletRequest request, HttpServletResponse response,Integer id) throws IOException {
        Map<String,Object> map=new HashMap<String, Object>();
        if(id!=null){
           boolean b= organService.deleteByPK(id);
            if(b){
                map.put("success",true);
            }else{
                map.put("success",false);
            }
            writeJSON(response,map);
        }
    }



    @RequestMapping(value = "/saveEmployee",method = {RequestMethod.GET, RequestMethod.POST})
    public void saveEmployee(HttpServletRequest request, HttpServletResponse response,Employee entity) throws IOException {

        JqueryBaseParameter jqueryBaseParameter=(JqueryBaseParameter)entity;

        try{
            if(CMD_EDIT.equals(jqueryBaseParameter.getCmd())){
                Employee employee=employeeService.get(entity.getId());
                employee.setName(entity.getName());
                employee.setAge(entity.getAge());
                employee.setSex(entity.getSex());
                employee.setTelephone(entity.getTelephone());
                employee.setMobile(entity.getMobile());
                employeeService.update(employee);
            }else if(CMD_NEW.equals(jqueryBaseParameter.getCmd())){
                entity.setCreateTime(new Date());
                employeeService.persist(entity);
            }
            jqueryBaseParameter.setCmd(CMD_EDIT);
            jqueryBaseParameter.setSuccess(true);
        }catch(Exception e){
            e.printStackTrace();
        }

        writeJSON(response,jqueryBaseParameter);
    }

    @RequestMapping(value = "/editEmployee",method = {RequestMethod.GET, RequestMethod.POST})
    public void editEmployee(HttpServletRequest request, HttpServletResponse response,Integer id) throws IOException {

        if(id!=null){
            Employee employee=employeeService.get(id);
            writeJSON(response,employee);
        }
    }

    @RequestMapping(value = "/delEmployee",method = {RequestMethod.GET, RequestMethod.POST})
    public void delEmployee(HttpServletRequest request, HttpServletResponse response,String ids) throws IOException {

        Map<String,String> msg=new HashMap<String,String>();
        try{
            if(ids!=null&& !"".equals(ids)){
                for(String id:ids.split(",")){
                    //Employee _obj=employeeService.get(Integer.parseInt(id));
                    User2 _obj=user2Service.getByProerties("employeeId",Integer.parseInt(id));
                    if(_obj!=null){
                        user2Service.deleteByPK(_obj.getId());
                    }
                    //这里不能简单的只删除empinfo表的数据 后期需要把相关表里的数据都删除掉
                    //empInfoService.deleteByProperties("empInfoId",Long.valueOf(id));
                    _obj=null;
                }
            }
            msg.put("msg", "success");
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","failed");
        }
        writeJSON(response,msg);
    }

    @RequestMapping(value = "/editUser",method = {RequestMethod.GET, RequestMethod.POST})
    public void editUser(HttpServletRequest request, HttpServletResponse response,Integer employeeId) throws IOException {

        if(employeeId!=null){
            User2 user2=user2Service.getByProerties("employeeId",employeeId);
            if(user2==null){
                writeJSON(response,new User2());
            }else{
                writeJSON(response,user2);
            }

        }
    }


    @RequestMapping(value = "/saveUser",method = {RequestMethod.GET, RequestMethod.POST})
    public void saveUser(HttpServletRequest request, HttpServletResponse response,User2 entity) throws IOException {

        JqueryBaseParameter jqueryBaseParameter=(JqueryBaseParameter)entity;

        try{
            if(CMD_EDIT.equals(jqueryBaseParameter.getCmd())){
                User2 user2=user2Service.get(entity.getId());
                user2.setLoginCode(entity.getLoginCode());
                user2.setPwd(MD5.crypt(entity.getPwd()));
                user2Service.update(user2);

                /*Employee employee=employeeService.get(entity.getEmployeeId());
                if(employee!=null){
                    employee.setSfUser(true);
                    employee.setUserId(user2.getId());
                }
                employeeService.update(employee);*/


            }else if(CMD_NEW.equals(jqueryBaseParameter.getCmd())){
                entity.setPwd(MD5.crypt(entity.getPwd()));
                user2Service.persist(entity);
                /*User2 user2=user2Service.getByProerties("loginCode",entity.getLoginCode());
                Employee employee=employeeService.get(entity.getEmployeeId());
                if(employee!=null){
                    employee.setSfUser(true);
                    employee.setUserId(user2.getId());
                }
                employeeService.update(employee);*/
            }

            jqueryBaseParameter.setCmd(CMD_EDIT);
            jqueryBaseParameter.setSuccess(true);
        }catch(Exception e){
            e.printStackTrace();
        }

        writeJSON(response,jqueryBaseParameter);
    }

    @RequestMapping(value = "/saveUserRole",method = {RequestMethod.GET, RequestMethod.POST})
    public void saveUserRole(HttpServletRequest request, HttpServletResponse response,String roles,Integer employeeId,String assignReason,String operateName) throws IOException {
        Map<String,String> msg=new HashMap<String,String>();
        Integer userId=0;
        if(roles!=null && employeeId!=null){
            User2 user2=user2Service.getByProerties("employeeId", employeeId);
            if(user2!=null)
                userId=user2.getId();
            userRoleService.deleteByProperties("userId",userId);
            String _roles[]=roles.split(",");
            String roleNames="";
            UserRole userRole=null;
            for(String s:_roles){
                userRole=new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleCode(s);
                userRoleService.persist(userRole);
                roleNames+=roleService.getByProerties("roleCode", s).getRoleName()+",";
            }
            
            UserRoleAssignLog log=new UserRoleAssignLog();
            log.setOperateName(operateName);
            log.setAssignReason(assignReason);
            log.setAssignTime(new Date());
            log.setRoleCode(roles);
            log.setUserId(userId);
           // log.setUserName(empInfoService.get(Long.valueOf(String.valueOf(employeeId))).getName());
            log.setRoleName(roleNames);
            userRoleAssignLogService.persist(log);
            
            
            msg.put("msg","success");
        }
        writeJSON(response,msg);
    }

    
    @RequestMapping(value = "/getUserRoleAssignLog",method = {RequestMethod.GET, RequestMethod.POST})
    public void getUserRoleAssignLog(HttpServletRequest request, HttpServletResponse response, Integer employeeId) throws IOException {
        if(employeeId!=null){
        	Integer userId=0;
        	User2 user2=user2Service.getByProerties("employeeId", employeeId);
            if(user2!=null)
                userId=user2.getId();
            Integer firstResult = Integer.valueOf(request.getParameter("page"));
            Integer pageSize = Integer.valueOf(request.getParameter("rows"));
            UserRoleAssignLog log=new UserRoleAssignLog();
            log.setFirstResult((firstResult - 1) * pageSize);
            log.setMaxResults(pageSize);
            log.set$eq_userId(userId);
            QueryResult<UserRoleAssignLog> queryResult=userRoleAssignLogService.doPaginationQuery(log);
            JqGridPageView jqGridPageView=new JqGridPageView();
            jqGridPageView.setRows(queryResult.getResultList());
            jqGridPageView.setTotal(queryResult.getTotalCount());
            writeJSON(response,jqGridPageView);
        }
    }
    
    private void recursion(Organ organ, Map<String, Object> map, List<Organ> totalData) {

        List<Organ> _chidrenList = new ArrayList<Organ>();
        _chidrenList = findChildrenNode(organ, totalData);
        List<Map<String, Object>> __childrenList = new ArrayList<Map<String, Object>>();
        if (_chidrenList.size() > 0) {
            Map<String, Object> map1 = null;
            for (Organ organ1 : _chidrenList) {
                map1 = new HashMap<String, Object>();
                map1.put("id", organ1.getId());
                map1.put("text", organ1.getOrgName());
                __childrenList.add(map1);
                recursion(organ1, map1, totalData);
            }
            map.put("children", __childrenList);
        }


    }

    private List<Organ> findChildrenNode(Organ organ, List<Organ> totalData) {
        List<Organ> list = new ArrayList<Organ>();
        for (Organ organ1 : totalData) {
            if (organ1.getParentCode().equals(organ.getOrgCode()))
                list.add(organ1);
        }
        return list;
    }
}
