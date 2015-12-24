package com.smallow.model.sys.param;

import core.support.JqueryBaseParameter;

/**
 * Created by smallow on 2015/4/29.
 */
public class ContentParameter extends JqueryBaseParameter {
    private Long $eq_merchantId;
    private Long [] $in_merchantId;

    public Long get$eq_merchantId() {
        return $eq_merchantId;
    }

    public void set$eq_merchantId(Long $eq_merchantId) {
        this.$eq_merchantId = $eq_merchantId;
    }


    public Long[] get$in_merchantId() {
        return $in_merchantId;
    }

    public void set$in_merchantId(Long[] $in_merchantId) {
        this.$in_merchantId = $in_merchantId;
    }
}
