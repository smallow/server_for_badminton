package com.smallow.model;

import com.smallow.model.param.MemberParameter;
import core.support.DateTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by smallow on 15/11/29.
 */
@Entity
@Table(name="badminton_member")
public class Member extends MemberParameter implements Serializable{

    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private String qq;

    @Column
    private String pwd;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinTime;

    @Column
    private Integer groupId;//群id

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}
