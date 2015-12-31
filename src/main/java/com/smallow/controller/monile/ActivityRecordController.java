package com.smallow.controller.monile;

import com.alibaba.fastjson.JSON;
import com.smallow.bean.ActivityRecordBean;
import com.smallow.bean.ApiResponse;
import com.smallow.bean.RegistrationPersonBean;
import com.smallow.model.ActivityRecord;
import com.smallow.model.Member;
import com.smallow.model.MembersActivityRecord;
import com.smallow.service.ActivityRecordService;
import com.smallow.service.MemberService;
import com.smallow.service.MembersActivityRecordService;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by smallow on 15/12/15.
 */
@Controller
@RequestMapping("/activity")
public class ActivityRecordController {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
    @Resource
    private ActivityRecordService activityRecordService;

    @Resource
    private MembersActivityRecordService membersActivityRecordService;

    @Resource
    private MemberService memberService;

    @RequestMapping(value = "/getTodayActivityRecord.do", method = {RequestMethod.POST, RequestMethod.GET})
    public void getTodayActivityRecord(HttpServletRequest request, HttpServletResponse response,Integer groupId) throws ParseException {

        ApiResponse<ActivityRecordBean> apiResponse;

        ActivityRecord activityRecord = activityRecordService.getByProerties(new String[]{"date","groupId"}, new Object[]{format.parse(format.format(new Date()))});
        //String memberIds = activityRecord.getMemberIds();
        String memberIds = "";
        if (activityRecord != null) {
            List<MembersActivityRecord> registrationMembersList=membersActivityRecordService.queryByProerties("activity_record_id",activityRecord.getId());
            if(registrationMembersList!=null && registrationMembersList.size()>0){
                for(MembersActivityRecord record:registrationMembersList){
                    memberIds+=record.getMember_id()+",";
                }
            }

            Integer[] memberId = new Integer[]{};
            List<Member> memberList = null;
            if (memberIds != null && !"".equals(memberIds)) {
                String[] _memberIds = memberIds.split(",");
                memberId = new Integer[_memberIds.length];
                for (int i = 0; i < _memberIds.length; i++) {
                    memberId[i] = Integer.parseInt(_memberIds[i]);
                }
            }

           // System.out.println("");

            if (memberId.length > 0) {
                Member member = new Member();
                member.set$in_id(memberId);
                member.setFirstResult(0);
                member.setMaxResults(100);
                memberList = memberService.doPaginationQuery(member, false).getResultList();
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
            record.setStartTime(format2.format(activityRecord.getStartTime()));
            record.setEndTime(format2.format(activityRecord.getEndTime()));
            record.setDate(format.format(activityRecord.getDate()));
            record.setVenue(activityRecord.getVenue());
            record.setDate_week(getWeekDayByDate(activityRecord.getDate()));
            record.setChargePerson(activityRecord.getChargePerson());
            record.setContactNumber(activityRecord.getContactNumber());
            record.setStatus(getStatusByCode(activityRecord.getStatus()));

            apiResponse = new ApiResponse<ActivityRecordBean>("0", "success");
            apiResponse.setObj(record);


        } else {
            apiResponse = new ApiResponse<ActivityRecordBean>("1", "empty data");
        }

        String json = JSON.toJSONString(apiResponse);
        ResponseUtils.renderJson(response, json);
    }


    @RequestMapping(value = "/registrateTodayActivity.do", method = {RequestMethod.POST, RequestMethod.GET})
    public void registrateTodayActivity(HttpServletRequest request, HttpServletResponse response,Integer memberId,Integer activityRecordId,Integer groupId) throws ParseException {
        ApiResponse<Void> apiResponse=null;

        try{
            MembersActivityRecord membersActivityRecord=membersActivityRecordService.getByProerties("activity_record_id",activityRecordId);
            if(membersActivityRecord==null){
                membersActivityRecord =new MembersActivityRecord();
                membersActivityRecord.setMember_id(memberId);
                membersActivityRecord.setActivity_record_id(activityRecordId);
                membersActivityRecord.setGroupId(groupId);
                membersActivityRecord.setCreateTime(new Date());
                membersActivityRecordService.persist(membersActivityRecord);
                apiResponse=new ApiResponse<Void>("0","success");
            }else{
                apiResponse=new ApiResponse<Void>("1","请不要重复报名!");
            }


        }catch (Exception e){
            e.printStackTrace();
            apiResponse=new ApiResponse<Void>("1","fail");
        }
        String json = JSON.toJSONString(apiResponse);
        ResponseUtils.renderJson(response, json);

    }

    private String getWeekDayByDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];

    }

    private String getStatusByCode(String statusCode){
        String status="";
        if(statusCode.equals("un_start")){
            status="未开始";
        }else if(statusCode.equals("started")){
            status="已开始";
        }else if(statusCode.equals("ended")){
            status="已结束";
        }else if(statusCode.equals("cancle")){
            status="已取消";
        }


        return status;
    }
}
