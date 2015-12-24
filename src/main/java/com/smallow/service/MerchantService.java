package com.smallow.service;

import com.smallow.model.merchant.Merchant;
import core.service.Service;

import java.util.List;

/**
 * Created by smallow on 2015/4/22.
 */
public interface MerchantService  extends Service<Merchant>{

    public List<Merchant> getMerchantViewList(List<Merchant> resultList);
}
