package com.smallow.controller.admin;

import com.smallow.core.Constant;
import com.smallow.core.JavaEEFrameworkBaseController;
import com.smallow.model.User;
import com.smallow.service.UserService;
import core.support.JqGridPageView;
import core.support.JqueryBaseParameter;
import core.support.QueryResult;
import core.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smallow on 2015/5/11.
 */

@Controller
@RequestMapping("/user")
public class UserController extends JavaEEFrameworkBaseController<User> implements Constant {

    @Resource
    private UserService userService;


    @RequestMapping("/manage")
    public String manage(){
        return "user/list";
    }


    @RequestMapping(value = "/_list", method = { RequestMethod.POST, RequestMethod.GET })
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer firstResult = Integer.valueOf(request.getParameter("page"));
        Integer pageSize = Integer.valueOf(request.getParameter("rows"));
        String telephpne=request.getParameter("telephone");
        String email=request.getParameter("email");

        User entity=new User();
        if(StringUtils.isNotBlank(telephpne)){
            entity.set$eq_telephone(telephpne);
        }
        if(StringUtils.isNotBlank(email)){
            entity.set$eq_email(email);
        }
        entity.setFirstResult((firstResult - 1) * pageSize);
        entity.setMaxResults(pageSize);
        QueryResult<User> queryResult=userService.doPaginationQuery(entity);
        JqGridPageView<User> merchantListView = new JqGridPageView<User>();
        merchantListView.setPageSize(pageSize);
        List<User> merchantList=queryResult.getResultList();
        merchantListView.setRows(merchantList);
        merchantListView.setTotal(queryResult.getTotalCount());
        writeJSON(response,merchantListView);
    }

    @RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET })
    public void saveUser(User entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JqueryBaseParameter jqueryBaseParameter=(JqueryBaseParameter)entity;
        User user=userService.getByProerties("id", entity.getId());
        if(null!=user && null==entity.getId()){
            jqueryBaseParameter.setSuccess(false);
        }else{
            try{
                if(CMD_EDIT.equals(jqueryBaseParameter.getCmd())){

                    user.setTelephone(entity.getTelephone());
                    user.setPwd(entity.getPwd());
                    user.setEmail(entity.getEmail());
                    userService.update(user);
                }else if(CMD_NEW.equals(jqueryBaseParameter.getCmd())){
                    entity.setPwd(MD5.crypt(entity.getPwd()));
                    entity.setCreateTime(new Date());
                    userService.persist(entity);
                }
                jqueryBaseParameter.setCmd(CMD_EDIT);
                jqueryBaseParameter.setSuccess(true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        writeJSON(response,jqueryBaseParameter);
    }




    @RequestMapping(value = "/del",method={RequestMethod.POST, RequestMethod.GET})
    public void delMerchant(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String ids=request.getParameter("ids");
        Map<String,String> msg=new HashMap<String,String>();
        try{
            if(ids!=null&& !"".equals(ids)){
                for(String id:ids.split(",")){
                    userService.deleteByPK(Long.valueOf(id));
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
