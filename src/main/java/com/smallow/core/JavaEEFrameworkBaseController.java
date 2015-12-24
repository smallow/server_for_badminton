package com.smallow.core;

import com.smallow.model.merchant.Merchant;
import core.controller.JqueryBaseController;
import core.support.JqueryBaseParameter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by smallow on 2015/4/22.
 */
public abstract class JavaEEFrameworkBaseController<E extends JqueryBaseParameter> extends JqueryBaseController<E> implements Constant {

    public Merchant getCurrentSysUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (Merchant) request.getSession().getAttribute(SESSION_SYS_USER);
    }

}
