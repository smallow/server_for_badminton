package com.smallow.controller.admin.merchant;

import com.smallow.core.Constant;
import com.smallow.core.JavaEEFrameworkBaseController;
import com.smallow.model.merchant.Merchant;
import com.smallow.service.MerchantService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smallow on 2015/4/22.
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController extends JavaEEFrameworkBaseController<Merchant> implements Constant {
    @Resource
    private MerchantService merchantService;

    @RequestMapping("/manage")
    public String manage(){
        return "merchant/manage";
    }

    @RequestMapping(value = "/_list", method = { RequestMethod.POST, RequestMethod.GET })
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer firstResult = Integer.valueOf(request.getParameter("page"));
        Integer pageSize = Integer.valueOf(request.getParameter("rows"));
        String name=request.getParameter("name");
        Merchant entity=new Merchant();
        if(StringUtils.isNotBlank(name)){
            entity.set$like_name(name);
        }

        entity.setFirstResult((firstResult - 1) * pageSize);
        entity.setMaxResults(pageSize);
        QueryResult<Merchant> queryResult=merchantService.doPaginationQuery(entity);
        JqGridPageView<Merchant> merchantListView = new JqGridPageView<Merchant>();
        merchantListView.setPageSize(pageSize);
        List<Merchant> merchantList=merchantService.getMerchantViewList(queryResult.getResultList());
        merchantListView.setRows(merchantList);
        merchantListView.setTotal(queryResult.getTotalCount());
        writeJSON(response,merchantListView);
    }

    @RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET })
    public void addMerchant(Merchant entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JqueryBaseParameter jqueryBaseParameter=(JqueryBaseParameter)entity;
        Merchant merchant=merchantService.getByProerties("id", entity.getId());
        if(null!=merchant && null==entity.getId()){
            jqueryBaseParameter.setSuccess(false);
        }else{
            try{
                if(CMD_EDIT.equals(jqueryBaseParameter.getCmd())){
                    entity.setLastLoginTime(merchant.getLastLoginTime());
                    merchant.setName(entity.getName());
                    merchant.setAddress(entity.getAddress());
                    merchant.setBigtype(entity.getBigtype());
                    merchant.setRegno(entity.getRegno());
                    merchant.setType(entity.getType());
                    merchant.setContactpersonname(entity.getContactpersonname());
                    merchant.setTelphone(entity.getTelphone());
                    merchant.setSfTop10(entity.getSfTop10());
                    merchant.setTop10Order(entity.getTop10Order());
                    merchantService.update(merchant);
                }else if(CMD_NEW.equals(jqueryBaseParameter.getCmd())){
                    entity.setPwd(MD5.crypt(entity.getPwd()));
                    merchantService.persist(entity);
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
                    merchantService.deleteByPK(Long.valueOf(id));
                }
            }
            msg.put("msg", "success");
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","failed");
        }
        writeJSON(response,msg);
    }
    @RequestMapping(value = "/edit",method={RequestMethod.POST, RequestMethod.GET})
    public void editMerchant(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Long id=Long.valueOf(String.valueOf(request.getParameter("id")));
        Merchant data=null;
        if(id!=null){
            try{
                data =merchantService.get(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        writeJSON(response,data);
    }
    @RequestMapping(value = "/contentManage",method = RequestMethod.GET)
    public String gotoMerchantManage(HttpServletRequest request,ModelMap model,Integer merchantId){
        if(merchantId!=null){
            model.addAttribute("merchantId",merchantId);
        }
        return "content/manage";
    }

}
