package com.smallow.model;

import com.smallow.model.param.MembersActivityRecordParameter;

import javax.persistence.*;


/**
 * Created by smallow on 15/12/29.
 */
@Entity
@Table(name = "badminton_members_activity_record")
public class MembersActivityRecord extends MembersActivityRecordParameter {

    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @Column
    private Integer member_id;
    @Column
    private Integer activity_record_id;

    @Column
    private Integer groupId;//群id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public Integer getActivity_record_id() {
        return activity_record_id;
    }

    public void setActivity_record_id(Integer activity_record_id) {
        this.activity_record_id = activity_record_id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
