package com.smallow.dao;

import com.smallow.bean.OrderBean;
import com.smallow.model.Order;
import core.dao.Dao;
import core.support.QueryResult;

import java.util.List;

/**
 * Created by smallow on 2015/5/6.
 */
public interface OrderDao  extends Dao<Order> {

    public QueryResult<OrderBean> doPaginationQuery(OrderBean bean);
    public List<OrderBean> getOrderBeanByContentIdAndMerchantId(Integer contentId,Long merchantId);
    public OrderBean getOrderBeanById(Integer id);
}
