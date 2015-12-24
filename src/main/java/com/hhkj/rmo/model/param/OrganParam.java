package com.hhkj.rmo.model.param;

import core.support.JqueryBaseParameter;

/**
 * Created by smallow on 2015/6/10.
 */
public class OrganParam  extends JqueryBaseParameter{

    private String $eq_orgCode;

    private Integer $eq_id;

    public String get$eq_orgCode() {
        return $eq_orgCode;
    }

    public void set$eq_orgCode(String $eq_orgCode) {
        this.$eq_orgCode = $eq_orgCode;
    }


    public Integer get$eq_id() {
        return $eq_id;
    }

    public void set$eq_id(Integer $eq_id) {
        this.$eq_id = $eq_id;
    }
}
