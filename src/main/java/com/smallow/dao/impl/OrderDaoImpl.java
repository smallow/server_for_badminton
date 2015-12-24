package com.smallow.dao.impl;

import com.smallow.bean.OrderBean;
import com.smallow.dao.OrderDao;
import com.smallow.model.Order;
import core.dao.BaseDao;
import core.support.QueryResult;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by smallow on 2015/5/6.
 */
@Repository
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public QueryResult<OrderBean> doPaginationQuery(OrderBean bean) {
        Integer firstResult=bean.getFirstResult();
        Integer maxResult=bean.getMaxResults();
        Long totalCount=0l;
        QueryResult<OrderBean> queryResult=new QueryResult<OrderBean>();
        StringBuffer sql=new StringBuffer("select v.id,v.title,v.orderNum,v.createTime,v.status,v.paymentType,v.telephone,v.contentNum,v.userVoucher,v.contentId,v.merchantId,v.totalMoney,v.realPaymentMoney,v.userId,v.titleImg,v.name from view_merchant_orders v where 1=1 ");
        StringBuffer countSql=new StringBuffer("select count(id) as num from view_merchant_orders v where 1=1 ");
        if(bean.getOrderNum()!=null){
            sql.append(" and v.orderNum='").append(bean.getOrderNum()).append("'");
            countSql.append(" and v.orderNum='").append(bean.getOrderNum()).append("'");
        }
        if(bean.getTitle()!=null){
            sql.append(" and v.title like '%").append(bean.getTitle()).append("%'");
            countSql.append(" and v.title like '%").append(bean.getTitle()).append("%'");
        }
        if(bean.getTelephone()!=null){
            sql.append(" and v.telephone='").append(bean.getTelephone()).append("'");
            countSql.append(" and v.telephone='").append(bean.getTelephone()).append("'");
        }
        if(bean.getUserVoucher()!=null){
            sql.append(" and v.userVoucher='").append(bean.getUserVoucher()).append("'");
            countSql.append(" and v.userVoucher='").append(bean.getUserVoucher()).append("'");
        }
        if(bean.getUserId()!=null){
            sql.append(" and v.userId=").append(bean.getUserId());
            countSql.append(" and v.userId=").append(bean.getUserId());
        }
        if(bean.getMerchantId()!=null){
            sql.append(" and v.merchantId=").append(bean.getMerchantId());
            countSql.append(" and v.merchantId=").append(bean.getMerchantId());
        }
        if(bean.getContentId()!=null){
            sql.append(" and v.contentId=").append(bean.getContentId());
            countSql.append(" and v.contentId=").append(bean.getContentId());
        }
        Query q=getSession().createSQLQuery(countSql.toString());
        List<Object> list=q.list();
        if(list!=null && list.size()>0){
            Object obj=list.get(0);
            totalCount=(Long.valueOf(String.valueOf(obj)));
            queryResult.setTotalCount(totalCount);
        }

        sql.append(" order by v.createTime desc limit ").append(firstResult).append(",").append(maxResult);
        q=getSession().createSQLQuery(sql.toString());
        List<Object[]> __list=new ArrayList<Object[]>();
        __list =q.list();
        List<OrderBean> _list=new ArrayList<OrderBean>();
        if(__list!=null && __list.size()>0){
            OrderBean _bean=null;
            for(Object [] obj:__list){
                _bean=new OrderBean();
                _bean.setId(Long.valueOf(String.valueOf(obj[0])));
                _bean.setTitle(String.valueOf(obj[1]));
                _bean.setOrderNum(String.valueOf(obj[2]));
                try {

                    _bean.setCreateTime(format.format((Date)obj[3]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                _bean.setStatus(String.valueOf(obj[4]));
                _bean.setPaymentType(String.valueOf(obj[5]));
                _bean.setTelephone(String.valueOf(obj[6]));
                _bean.setContentNum(Integer.parseInt(String.valueOf(obj[7])));
                _bean.setUserVoucher(String.valueOf(obj[8] == null ? "" : obj[8]));
                _bean.setTotalMoney(BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[11] == null ? "0" : obj[11]))));
                _bean.setRealPaymentMoney(BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[12] == null ? "0" : obj[12]))));
                _bean.setUserId(Long.valueOf(String.valueOf(obj[13] == null ? "0" : obj[13])));
                if(obj[14]!=null){
                    String firstTitleImg=String.valueOf(obj[14]).split(",")[0];
                    _bean.setFirstTitleImg(firstTitleImg);
                }
                _bean.setName(String.valueOf(obj[15]));
                _list.add(_bean);
            }
        }
        queryResult.setResultList(_list);
        return queryResult;

    }

    @Override
    public List<OrderBean> getOrderBeanByContentIdAndMerchantId(Integer contentId, Long merchantId) {
        List<OrderBean> _list=new ArrayList<OrderBean>();
        String sql="select * from view_merchant_orders v where v.contentId="+contentId+" and v.merchantId="+merchantId;
        Query q=getSession().createSQLQuery(sql.toString());
        List<Object[]> list=q.list();
        OrderBean bean=null;
        if(list!=null && list.size()>0){
            for(Object [] obj:list){
                bean=new OrderBean();
                OrderBeanViewConvert(bean,obj);
                _list.add(bean);
            }
        }
        return _list;
    }

    @Override
    public OrderBean getOrderBeanById(Integer id) {
        String sql="select * from view_merchant_orders v where v.id="+id;
        Query q=getSession().createSQLQuery(sql.toString());
        List<Object[]> list=q.list();
        OrderBean bean=null;
        if(list!=null && list.size()>0){
            for(Object [] obj:list){
                bean=new OrderBean();
                OrderBeanViewConvert(bean, obj);
            }
        }
        return bean;
    }


    private void OrderBeanViewConvert(OrderBean newOrderBean,Object [] dbResult){

        if(dbResult!=null && dbResult.length>0){
            newOrderBean.setId(Long.valueOf(String.valueOf(dbResult[0])));
            newOrderBean.setTitle(String.valueOf(dbResult[1]));
            newOrderBean.setOrderNum(String.valueOf(dbResult[2]));
            newOrderBean.setContentId(Integer.parseInt(String.valueOf(dbResult[3])));
            newOrderBean.setMerchantId(Long.valueOf(String.valueOf(dbResult[4])));
            newOrderBean.setOriginalPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(dbResult[5] == null ? "0" : dbResult[5]))));
            newOrderBean.setConcessionalPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(dbResult[6] == null ? "0" : dbResult[6]))));
            newOrderBean.setContentNum(Integer.parseInt(String.valueOf(dbResult[7] == null ? "0" : dbResult[7])));
            newOrderBean.setTotalMoney(BigDecimal.valueOf(Double.valueOf(String.valueOf(dbResult[8] == null ? "0" : dbResult[8]))));
            newOrderBean.setRealPaymentMoney(BigDecimal.valueOf(Double.valueOf(String.valueOf(dbResult[9] == null ? "0" : dbResult[9]))));
            try {
                newOrderBean.setCreateTime(format.format((Date) dbResult[10] == null ? "" : dbResult[10]));
                //newOrderBean.setCreateTime(String.valueOf(dbResult[10] == null ? "2015-01-01 00:00:01" : dbResult[10]));
            } catch (Exception e) {
                e.printStackTrace();
            }
            newOrderBean.setPaymentType(String.valueOf(dbResult[11]==null?"":dbResult[11]));
            newOrderBean.setTelephone(String.valueOf(dbResult[12] == null ? "" : dbResult[12]));
            newOrderBean.setName(String.valueOf(dbResult[13]));
            newOrderBean.setStatus(String.valueOf(dbResult[16]));
            newOrderBean.setUserVoucher(String.valueOf(dbResult[18]));
        }
    }
}
