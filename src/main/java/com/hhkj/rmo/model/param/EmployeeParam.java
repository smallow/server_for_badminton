package com.hhkj.rmo.model.param;

import core.support.JqueryBaseParameter;

/**
 * Created by smallow on 2015/6/11.
 */
public class EmployeeParam extends JqueryBaseParameter {

    private Integer $eq_orgId;

    public Integer get$eq_orgId() {
        return $eq_orgId;
    }

    public void set$eq_orgId(Integer $eq_orgId) {
        this.$eq_orgId = $eq_orgId;
    }
}
