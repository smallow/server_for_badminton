package com.hhkj.rmo.model;

import javax.persistence.*;

/**
 * Created by smallow on 2015/6/17.
 */
@Entity
@Table(name = "rmo_business_module")
public class BusinessModule  {

    @Id
    @Column(name = "i_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "s_name")
    private String name;

    @Column(name = "s_code")
    private String code;

    @Column(name = "s_desc")
    private String description;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
