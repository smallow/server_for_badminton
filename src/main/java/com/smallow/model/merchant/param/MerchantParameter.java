package com.smallow.model.merchant.param;

import com.sun.org.apache.xpath.internal.operations.Bool;
import core.support.JqueryBaseParameter;

/**
 * Created by smallow on 2015/4/22.
 */
public class MerchantParameter  extends JqueryBaseParameter{
    private static final long serialVersionUID = 7656443663108619135L;
    private String $like_name;
    private String cn_bigType;
    private String cn_midType;

    private Boolean $eq_sfTop10;

    public Boolean get$eq_sfTop10() {
        return $eq_sfTop10;
    }

    public void set$eq_sfTop10(Boolean $eq_sfTop10) {
        this.$eq_sfTop10 = $eq_sfTop10;
    }

    public String get$like_name() {
        return $like_name;
    }

    public void set$like_name(String $like_name) {
        this.$like_name = $like_name;
    }


    public String getCn_bigType() {
        return cn_bigType;
    }

    public void setCn_bigType(String cn_bigType) {
        this.cn_bigType = cn_bigType;
    }

    public String getCn_midType() {
        return cn_midType;
    }

    public void setCn_midType(String cn_midType) {
        this.cn_midType = cn_midType;
    }
}
