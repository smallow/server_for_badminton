package com.smallow.dao.impl;

import com.smallow.dao.ActivityRecordDao;
import com.smallow.model.ActivityRecord;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 15/12/15.
 */
@Repository
public class ActivityRecordDaoImpl extends BaseDao<ActivityRecord> implements ActivityRecordDao {


    public ActivityRecordDaoImpl() {
        super(ActivityRecord.class);
    }
}
