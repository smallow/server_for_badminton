package com.smallow.controller.monile;

import com.alibaba.fastjson.JSON;
import com.smallow.bean.ActivityRecordBean;
import com.smallow.bean.ApiResponse;
import com.smallow.bean.RegistrationPersonBean;
import com.smallow.model.ActivityRecord;
import com.smallow.model.Member;
import com.smallow.service.ActivityRecordService;
import com.smallow.service.MemberService;
import core.util.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by smallow on 15/12/15.
 */
@Controller
@RequestMapping("/activity")
public class ActivityRecordController {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Resource
    private ActivityRecordService activityRecordService;

    @Resource
    private MemberService memberService;

    @RequestMapping(value = "/getTodayActivityRecord.do", method = {RequestMethod.POST, RequestMethod.GET})
    public void getTodayActivityRecord(HttpServletRequest request, HttpServletResponse response) throws ParseException {

        ApiResponse<ActivityRecordBean> apiResponse = new ApiResponse<ActivityRecordBean>("0", "success");

        ActivityRecord activityRecord = activityRecordService.getByProerties("date", format.parse(format.format(new Date())));
        String memberIds = activityRecord.getMemberIds();
        Integer[] memberId = new Integer[]{};
        List<Member> memberList = null;
        if (memberIds != null) {
            String[] _memberIds = memberIds.split(",");
            memberId = new Integer[_memberIds.length];
            for (int i = 0; i < _memberIds.length; i++) {
                memberId[i] = Integer.parseInt(_memberIds[i]);
            }
        }

        System.out.println("");

        if (memberId.length > 0) {
            Member member = new Member();
            member.set$in_id(memberId);
            memberList = memberService.doQuery(member);
        }
        List<RegistrationPersonBean> list = new ArrayList<RegistrationPersonBean>();
        if (memberList != null && memberList.size() > 0) {
            RegistrationPersonBean person = null;
            for (Member member : memberList) {
                person = new RegistrationPersonBean();
                person.setId(String.valueOf(member.getId()));
                person.setName(member.getName());
                person.setQq(member.getQq());
                list.add(person);
            }
        }


        ActivityRecordBean record = new ActivityRecordBean();
        record.setId(activityRecord.getId());
        record.setPersons(list);
        record.setChargePerson(activityRecord.getChargePerson());
        record.setBadmintonNum(activityRecord.getBadmintonNum());
        apiResponse.setObj(record);

        String json = JSON.toJSONString(apiResponse);
        ResponseUtils.renderJson(response, json);
    }
}
