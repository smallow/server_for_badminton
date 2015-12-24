package com.smallow.service.impl;

import com.smallow.dao.MerchantDao;
import com.smallow.model.merchant.Merchant;
import com.smallow.service.MerchantService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by smallow on 2015/4/24.
 */
@Service
public class MerchantServiceImpl extends BaseService<Merchant> implements MerchantService {

    private MerchantDao merchantDao;



    public List<Merchant> getMerchantViewList(List<Merchant> resultList) {
        List<Merchant> list=new ArrayList<Merchant>();
        if(resultList!=null && resultList.size()>0){
            Merchant obj=null;
            for(Merchant merchant:resultList){
                obj=new Merchant();
                obj.setId(merchant.getId());
                obj.setName(merchant.getName());
                obj.setAddress(merchant.getAddress());
                obj.setContactpersonname(merchant.getContactpersonname());
                obj.setTelphone(merchant.getTelphone());
                obj.setMobilephone(merchant.getMobilephone());
                obj.setPwd(merchant.getPwd());
                if(merchant.getBigtype().equals("10001")){
                    obj.setCn_bigType("超市");
                }else if(merchant.getBigtype().equals("20001")){
                    obj.setCn_bigType("孕婴生活");
                }else if(merchant.getBigtype().equals("30001")){
                    obj.setCn_bigType("美容美发");
                }else if(merchant.getBigtype().equals("40001")){
                    obj.setCn_bigType("美食");
                }else{

                }
                obj.setMidtype(merchant.getMidtype());

                if(merchant.getType().equals("001")){
                    obj.setType("公司企业");
                }else if(merchant.getType().equals("002")){
                    obj.setType("个人体工商户 ");
                }else if(merchant.getType().equals("003")){
                    obj.setType("农村信用合作社");
                }else{

                }
                obj.setTop10Order(merchant.getTop10Order());
                obj.setSfTop10(merchant.getSfTop10());
                list.add(obj);
            }
        }
        return list;
    }

    @Resource
    public void setMerchantDao(MerchantDao merchantDao) {
        this.merchantDao = merchantDao;
        this.dao = merchantDao;
    }
}
