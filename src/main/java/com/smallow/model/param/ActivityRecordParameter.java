package com.smallow.model.param;

import core.support.JqueryBaseParameter;

import java.util.Date;

/**
 * Created by smallow on 15/12/15.
 */
public class ActivityRecordParameter extends JqueryBaseParameter {

    private Date $eq_date;



    public Date get$eq_date() {
        return $eq_date;
    }

    public void set$eq_date(Date $eq_date) {
        this.$eq_date = $eq_date;
    }
}
