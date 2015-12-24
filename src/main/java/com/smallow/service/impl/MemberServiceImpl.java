package com.smallow.service.impl;

import com.smallow.dao.MemberDao;
import com.smallow.model.Member;
import com.smallow.service.MemberService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 15/12/15.
 */
@Service
public class MemberServiceImpl extends BaseService<Member> implements MemberService {

    private MemberDao memberDao;


    @Resource
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        this.dao = memberDao;
    }
}
