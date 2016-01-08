package com.smallow.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by smallow on 15/12/15.
 */
public class ActivityRecordBean {

    public static final String STATUS_UN_START = "un_start";//未开始
    public static final String STATUS_STARTED = "started";//已开始
    public static final String STATUS_ENDED = "ended";//已结束
    public static final String STATUS_CANCLE = "cancle";//已取消


    private Integer id;
    private String date;
    private String startTime;
    private String endTime;
    private String chargePerson;//负责人
    private String contactNumber;//联系电话
    private String status;//状态


    private Integer playFieldNum;//场地数目
    private Integer badmintonNum;//使用的羽毛球数目
    private BigDecimal totalMoney;//总费用
    private Integer registrationNum;//报名人数
    private BigDecimal avgMoney;//人均费用
    private Integer groupId;//所属群id

    private List<RegistrationPersonBean> persons;//报名人

    private String date_week;//星期几
    private String venue;//球馆

    private Integer createPersonId;//活动发起人ID
    private String createTime;//活动创建时间



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPlayFieldNum() {
        return playFieldNum;
    }

    public void setPlayFieldNum(Integer playFieldNum) {
        this.playFieldNum = playFieldNum;
    }

    public Integer getBadmintonNum() {
        return badmintonNum;
    }

    public void setBadmintonNum(Integer badmintonNum) {
        this.badmintonNum = badmintonNum;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(Integer registrationNum) {
        this.registrationNum = registrationNum;
    }

    public BigDecimal getAvgMoney() {
        return avgMoney;
    }

    public void setAvgMoney(BigDecimal avgMoney) {
        this.avgMoney = avgMoney;
    }

    public List<RegistrationPersonBean> getPersons() {
        return persons;
    }

    public void setPersons(List<RegistrationPersonBean> persons) {
        this.persons = persons;
    }


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getDate_week() {
        return date_week;
    }

    public void setDate_week(String date_week) {
        this.date_week = date_week;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Integer getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(Integer createPersonId) {
        this.createPersonId = createPersonId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
