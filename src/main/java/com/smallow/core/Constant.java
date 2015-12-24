package com.smallow.core;

/**
 * Created by smallow on 2015/4/22.
 */
public interface Constant {
    public static final String SESSION_SYS_USER = "SESSION_SYS_USER";

    public static final String JEEFW_DATA_SOURCE_BEAN_ID = "smallowDataSource";

    public static final String ORDER_STATUS_YTJ="01";//订单已提交
    public static final String ORDER_STATUS_YFK="02";//已付款
    public static final String ORDER_STATUS_YXF="03";//已消费完结


    public static final String PAYMENT_TYPE_ZFB="01";//支付宝
    public static final String PAYMENT_TYPE_WX="02";//微信
    public static final String PAYMENT_TYPE_YHK="03";//网银
    public static final String PAYMENT_TYPE_DF="04";//到付


}
