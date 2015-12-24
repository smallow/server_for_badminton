package com.smallow.model.param;

import core.support.JqueryBaseParameter;

/**
 * Created by smallow on 2015/5/11.
 */
public class UserParameter extends JqueryBaseParameter {

    private String $eq_telephone;

    private String $eq_email;


    public String get$eq_telephone() {
        return $eq_telephone;
    }

    public void set$eq_telephone(String $eq_telephone) {
        this.$eq_telephone = $eq_telephone;
    }

    public String get$eq_email() {
        return $eq_email;
    }

    public void set$eq_email(String $eq_email) {
        this.$eq_email = $eq_email;
    }
}
