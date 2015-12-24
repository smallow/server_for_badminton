package com.smallow.service.impl;

import com.smallow.dao.ActivityRecordDao;
import com.smallow.model.ActivityRecord;
import com.smallow.service.ActivityRecordService;
import core.service.BaseService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by smallow on 15/12/15.
 */
@Service
public class ActivityRecordServiceImpl extends BaseService<ActivityRecord> implements ActivityRecordService {

    private ActivityRecordDao activityRecordDao;

    @Resource
    public void setActivityRecordDao(ActivityRecordDao activityRecordDao) {
        this.activityRecordDao = activityRecordDao;
        this.dao = activityRecordDao;
    }
}
