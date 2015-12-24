package com.smallow.controller.admin;

import com.smallow.bean.OrderBean;
import com.smallow.core.Constant;
import com.smallow.core.JavaEEFrameworkBaseController;
import com.smallow.model.Order;
import com.smallow.model.sys.Content;
import com.smallow.service.ContentService;
import com.smallow.service.OrderService;
import core.support.JqGridPageView;
import core.support.QueryResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smallow on 2015/5/6.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends JavaEEFrameworkBaseController<OrderBean> implements Constant {

    @Resource
    private OrderService orderService;
    @Resource
    private ContentService contentService;

    @RequestMapping(value = "/queryOrder",method = {RequestMethod.GET,RequestMethod.POST})
    public String home(HttpServletRequest request,ModelMap model,Integer merchantId){
        if(merchantId!=null){
            model.addAttribute("merchantId",merchantId);
        }
        return "order/list";
    }

    @RequestMapping(value = "/_list",method = {RequestMethod.POST,RequestMethod.GET})
    public void list(HttpServletRequest request,HttpServletResponse response,ModelMap model,Long merchantId,String status) throws IOException {
        if(merchantId!=null && status!=null){
            Integer firstResult = Integer.valueOf(request.getParameter("page"));
            Integer pageSize = Integer.valueOf(request.getParameter("rows"));
            OrderBean entity=new OrderBean();
            entity.setFirstResult((firstResult - 1) * pageSize);
            entity.setMaxResults(pageSize);
            entity.setStatus(status);
            entity.setMerchantId(merchantId);
            QueryResult<OrderBean> queryResult=orderService.doPaginationQuery(entity);
            JqGridPageView<OrderBean> orderListView = new JqGridPageView<OrderBean>();
            orderListView.setPageSize(pageSize);
            List<OrderBean> list=queryResult.getResultList();
            orderListView.setTotal(queryResult.getTotalCount());
            orderListView.setRows(list);
            writeJSON(response,orderListView);
        }
    }
    @RequestMapping(value = "/placeOrder")
    public void placeOrder(HttpServletRequest request,HttpServletResponse response,ModelMap model,Integer contentId,Long merchantId) throws IOException {
        if(contentId!=null && merchantId!=null){
           // OrderBean bean=orderService.getOrderBeanByContentIdAndMerchantId(contentId,merchantId);
            Content bean=contentService.get(contentId);
            writeJSON(response,bean);
        }
    }
    @RequestMapping(value = "/saveOrderFirstStep",method = {RequestMethod.POST,RequestMethod.GET})
    public void saveOrderFirstStep(HttpServletRequest request,HttpServletResponse response,Order entity,String title) throws IOException {
        Map<String,Object> map=new HashMap<String, Object>();
        if(entity!=null){
            try{
                String orderNum=createOrderNum(entity.getUserId()==null?999l:entity.getUserId(),entity.getContentId());
                entity.setOrderNum(orderNum);
                entity.setCreateTime(new Date());
                orderService.persist(entity);
                map.put("msg", "success");
                map.put("orderNum", entity.getOrderNum());
                map.put("contentNum",entity.getContentNum());
                map.put("realPaymentMoney",entity.getRealPaymentMoney());
                map.put("title",title);
                map.put("contentId",entity.getContentId());
                map.put("telephone",entity.getTelephone());
            }catch (Exception e){
                e.printStackTrace();
                map.put("msg","failed");
            }
            writeJSON(response, map);
        }
    }


    @RequestMapping(value = "/payOrderSecondStep",method = {RequestMethod.POST,RequestMethod.GET})
    public void payOrderSecondStep(HttpServletRequest request,HttpServletResponse response,String orderNum,String paymentType) throws IOException {
        Map<String,Object> map=new HashMap<String, Object>();
        if(orderNum!=null && !orderNum.equals("")){
            Order entity=orderService.getByProerties("orderNum",orderNum);
            entity.setPaymentType(paymentType);
            entity.setStatus("02");
            String userVoucher=createUserVoucher(999l,orderNum);
            entity.setUserVoucher(userVoucher);
            orderService.update(entity);
            map.put("msg", "success");
        }
        writeJSON(response,map);

    }


    @RequestMapping(value = "/updateOrder",method = {RequestMethod.POST,RequestMethod.GET})
    public void payOrderSecondStep(HttpServletRequest request,HttpServletResponse response,Long id,Integer contentNum,String telephone,BigDecimal realPaymentMoney,BigDecimal totalMoney,String paymentgType,String status) throws IOException {
        Map<String,Object> map=new HashMap<String, Object>();
        if(id!=null){
            Order bean=orderService.getByProerties("id",id);
            bean.setContentNum(contentNum);
            bean.setTelephone(telephone);
            bean.setTotalMoney(totalMoney);
            bean.setRealPaymentMoney(realPaymentMoney);
            bean.setPaymentType(paymentgType);
            bean.setStatus(status);
            String userVoucher=createUserVoucher(999l,bean.getOrderNum());
            bean.setUserVoucher(userVoucher);
            orderService.update(bean);
            map.put("msg", "success");
        }
        writeJSON(response,map);

    }

    @RequestMapping(value = "/getOrdersByUserId",method = {RequestMethod.POST,RequestMethod.GET})
    public void getOrdersByUserId(HttpServletRequest request,HttpServletResponse response,Long userId) throws IOException {
        if(userId!=null){
            OrderBean entity=new OrderBean();
            entity.setFirstResult(0);
            entity.setMaxResults(10);
            entity.setUserId(userId);
            QueryResult<OrderBean> queryResult=orderService.doPaginationQuery(entity);
            List<OrderBean> list=queryResult.getResultList();
            writeJSON(response,list);
        }
    }


    @RequestMapping(value = "/getOrderDetail",method = {RequestMethod.GET,RequestMethod.POST})
    public void getOrderDetail(HttpServletRequest request,HttpServletResponse response,Integer orderId) throws IOException {
        if(orderId!=null && orderId!=null){
            OrderBean bean=orderService.getOrderBeanById(orderId);
            writeJSON(response,bean);
        }
    }

    private String createOrderNum(Long userId,Integer contentId){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String orderNum=String.valueOf(userId)+sdf.format(new Date())+contentId;
        return orderNum;
    }

    private String createUserVoucher(Long userId,String orderNum){
        String userVoucher="http://www.smallow.com/userVoucher?userId="+userId+"&orderNum="+orderNum;
        return userVoucher;
    }
}
