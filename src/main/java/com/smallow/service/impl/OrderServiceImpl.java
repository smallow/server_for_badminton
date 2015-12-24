package com.smallow.service.impl;

import com.smallow.bean.OrderBean;
import com.smallow.dao.OrderDao;
import com.smallow.model.Order;
import com.smallow.service.OrderService;
import core.service.BaseService;
import core.support.QueryResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by smallow on 2015/5/6.
 */
@Service
public class OrderServiceImpl extends BaseService<Order> implements OrderService {

    private OrderDao orderDao;

    @Resource
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
        this.dao=orderDao;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public QueryResult<OrderBean> doPaginationQuery(OrderBean bean) {
        return orderDao.doPaginationQuery(bean);
    }



    @Override
    public List<OrderBean> getOrderBeanByContentIdAndMerchantId(Integer contentId, Long merchantId) {
        return orderDao.getOrderBeanByContentIdAndMerchantId(contentId,merchantId);
    }

    @Override
    public OrderBean getOrderBeanById(Integer id) {
        return orderDao.getOrderBeanById(id);
    }
}
