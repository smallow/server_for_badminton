package com.springapp.mvc.test;

/**
 * Created by smallow on 2015/4/29.
 */
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="husband",catalog="smallow")
public class Husband {

    private int id;
    private String name;
    private Wife wife;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Column(name="name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @OneToOne(cascade=CascadeType.ALL,mappedBy="husband")
    public Wife getWife() {
        return wife;
    }
    public void setWife(Wife wife) {
        this.wife = wife;
    }

}
