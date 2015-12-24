package com.smallow.service;

import com.smallow.bean.OrderBean;
import com.smallow.model.Order;
import core.service.Service;
import core.support.QueryResult;

import java.util.List;

/**
 * Created by smallow on 2015/5/6.
 */
public interface OrderService extends Service<Order> {

    public QueryResult<OrderBean> doPaginationQuery(OrderBean bean);

    public List<OrderBean> getOrderBeanByContentIdAndMerchantId(Integer contentId,Long merchantId);

    public OrderBean getOrderBeanById(Integer id);



}
