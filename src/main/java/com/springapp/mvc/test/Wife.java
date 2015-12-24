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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="wife",catalog="smallow")
public class Wife {

    private int id;
    private String name;
    private Husband husband;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
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
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="husband_id")
    public Husband getHusband() {
        return husband;
    }
    public void setHusband(Husband husband) {
        this.husband = husband;
    }

}