package com.smallow.dao.impl;

import com.smallow.dao.MembersActivityRecordDao;
import com.smallow.model.MembersActivityRecord;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 15/12/30.
 */
@Repository
public class MembersActivityRecordDaoImpl extends BaseDao<MembersActivityRecord> implements MembersActivityRecordDao {

    public MembersActivityRecordDaoImpl() {
        super(MembersActivityRecord.class);
    }
}
