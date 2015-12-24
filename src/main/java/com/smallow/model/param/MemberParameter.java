package com.smallow.model.param;

import core.support.JqueryBaseParameter;

/**
 * Created by smallow on 15/11/30.
 */
public class MemberParameter extends JqueryBaseParameter {

    private Integer[] $in_id;

    public Integer[] get$in_id() {
        return $in_id;
    }

    public void set$in_id(Integer[] $in_id) {
        this.$in_id = $in_id;
    }
}
