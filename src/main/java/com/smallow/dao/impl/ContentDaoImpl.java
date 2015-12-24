package com.smallow.dao.impl;

import com.smallow.bean.ContentBean;
import com.smallow.dao.ContentDao;
import com.smallow.model.merchant.Merchant;
import com.smallow.model.sys.Content;
import core.dao.BaseDao;
import core.support.QueryResult;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by smallow on 2015/4/29.
 */
@Repository
public class ContentDaoImpl extends BaseDao<Content> implements ContentDao {
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public ContentDaoImpl() {
        super(Content.class);
    }

    @Override
    public QueryResult<ContentBean> doPaginationQuery(ContentBean bean) {
        Integer firstResult=bean.getFirstResult();
        Integer maxResult=bean.getMaxResults();
        Long totalCount=0l;
        QueryResult<ContentBean> queryResult=new QueryResult<ContentBean>();
        StringBuffer sql=new StringBuffer(" select v.id, v.title,v.originalPrice,v.concessionalPrice,v.titleImg,v.name,v.merchantId from view_content_merchant v where 1=1 ");
        StringBuffer countSql=new StringBuffer("select count(id) as num from view_content_merchant v where 1=1 ");

        if(bean.getMerchant()!=null && bean.getMerchant().getId()!=null){
            sql.append(" and v.merchantId=").append(bean.getMerchant().getId());
            countSql.append(" and v.merchantId=").append(bean.getMerchant().getId());
        }

        Query q=getSession().createSQLQuery(countSql.toString());
        List<Object> list=q.list();
        if(list!=null && list.size()>0){
            Object obj=list.get(0);
            totalCount=(Long.valueOf(String.valueOf(obj)));
            queryResult.setTotalCount(totalCount);
        }
        sql.append(" order by v.id desc limit ").append(firstResult).append(",").append(maxResult);
        q=getSession().createSQLQuery(sql.toString());
        List<Object[]> __list=new ArrayList<Object[]>();
        __list =q.list();
        List<ContentBean> _list=new ArrayList<ContentBean>();
        if(__list!=null && __list.size()>0){
            ContentBean _bean=null;
            Merchant merchant=null;
            for(Object [] obj:__list){
                _bean=new ContentBean();
                merchant=new Merchant();
                _bean.setId(Integer.parseInt(String.valueOf(obj[0])));
                _bean.setTitle(String.valueOf(obj[1]));
                _bean.setOriginalPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[2] == null ? "0" : obj[2]))));
                _bean.setConcessionalPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[3] == null ? "0" : obj[3]))));
               // _bean.setUserId(Long.valueOf(String.valueOf(obj[13] == null ? "0" : obj[13])));
                if(obj[4]!=null){
                    String firstTitleImg=String.valueOf(obj[4]).split(",")[0];
                    _bean.setTitleImg(firstTitleImg);
                }
                merchant.setName(String.valueOf(obj[5]));
                merchant.setId(Long.valueOf(String.valueOf(obj[6])));
                _bean.setMerchant(merchant);
                _list.add(_bean);
            }
        }
        queryResult.setResultList(_list);
        return queryResult;


    }

    @Override
    public ContentBean getContentBeanById(Integer contentId) {
        StringBuffer sql=new StringBuffer(" select v.id, v.title,v.originalPrice,v.concessionalPrice,v.titleImg,v.name,v.merchantId,v.effectDate,v.telphone,v.mobilephone,v.address, v.needappointment    from view_content_merchant v  where id="+contentId);
        Query q=getSession().createSQLQuery(sql.toString());
        List<Object[]> __list=new ArrayList<Object[]>();
        __list =q.list();

        if(__list!=null && __list.size()>0){
            ContentBean _bean=null;
            Merchant merchant=null;
            Object[] obj=__list.get(0);
                _bean=new ContentBean();
                merchant=new Merchant();
                _bean.setId(Integer.parseInt(String.valueOf(obj[0])));
                _bean.setTitle(String.valueOf(obj[1]));
                _bean.setOriginalPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[2] == null ? "0" : obj[2]))));
                _bean.setConcessionalPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[3] == null ? "0" : obj[3]))));
                // _bean.setUserId(Long.valueOf(String.valueOf(obj[13] == null ? "0" : obj[13])));
                if(obj[4]!=null){
                    String firstTitleImg=String.valueOf(obj[4]).split(",")[0];
                    _bean.setTitleImg(firstTitleImg);
                }
                merchant.setName(String.valueOf(obj[5]));
                merchant.setId(Long.valueOf(String.valueOf(obj[6])));
                _bean.setMerchant(merchant);
                _bean.setEffectDate(format.format((Date) obj[7]));
                _bean.setTelphone(String.valueOf(obj[8]));
                _bean.setMobilephone(String.valueOf(obj[9]));
                _bean.setAddress(String.valueOf(obj[10]));
                _bean.setNeedAppointMent((Boolean)obj[11]);
            return _bean;

        }
        return null;
    }
}
