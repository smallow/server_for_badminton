package com.smallow.model;

import com.smallow.model.param.GroupParameter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by smallow on 15/12/15.
 */
@Entity
@Table(name = "badminton_group")
public class Group extends GroupParameter implements Serializable{
    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private String qq;

    @Column
    private String groupMaster;//群主

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

    public String getGroupMaster() {
        return groupMaster;
    }

    public void setGroupMaster(String groupMaster) {
        this.groupMaster = groupMaster;
    }
}
