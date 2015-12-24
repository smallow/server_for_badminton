package com.smallow.dao.impl;

import com.smallow.dao.MemberDao;
import com.smallow.model.Member;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 15/12/15.
 */
@Repository
public class MemberDaoImpl extends BaseDao<Member> implements MemberDao {

    public MemberDaoImpl() {
        super(Member.class);
    }
}
