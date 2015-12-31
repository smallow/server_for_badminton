package com.smallow.service.impl;

import com.smallow.dao.MembersActivityRecordDao;
import com.smallow.model.MembersActivityRecord;
import com.smallow.service.MembersActivityRecordService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 15/12/30.
 */
@Service
public class MembersActivityRecordServiceImpl extends BaseService<MembersActivityRecord> implements MembersActivityRecordService {

    private MembersActivityRecordDao membersActivityRecordDao;

    @Resource
    public void setMembersActivityRecordDao(MembersActivityRecordDao membersActivityRecordDao) {
        this.membersActivityRecordDao = membersActivityRecordDao;
        this.dao = membersActivityRecordDao;
    }
}
