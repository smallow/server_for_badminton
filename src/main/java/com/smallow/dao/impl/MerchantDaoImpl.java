package com.smallow.dao.impl;

import com.smallow.dao.MerchantDao;
import com.smallow.model.merchant.Merchant;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 2015/4/22.
 */
@Repository
public class MerchantDaoImpl extends BaseDao<Merchant> implements MerchantDao    {
    public MerchantDaoImpl(){
        super(Merchant.class);
    }
}
