package com.hhkj.rmo.service;

import com.hhkj.rmo.model.User2;
import core.service.Service;

import java.io.Serializable;

/**
 * Created by smallow on 2015/6/12.
 */
public interface User2Service extends Service<User2> {
    @Override
    boolean deleteByPK(Serializable... id);
}
