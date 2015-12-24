package com.springapp.mvc.test;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by smallow on 2015/4/28.
 */
@Entity
@Table(name="tb_content")
public class Content {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name = "sort_date" )
    @Temporal(TemporalType.TIMESTAMP)
    private Date sortDate;
    @Column(name = "top_level")
    private Byte topLevel;
    @Column(name = "has_title_img")
    private Boolean hasTitleImg;
    @Column(name = "is_recommend")
    private Boolean recommend;
    @Column(name = "status")
    private Byte status;
    @Column(name = "views_day")
    private Integer viewsDay;
    @Column(name = "comments_day")
    private Short commentsDay;
    @Column(name = "merchant_id")
    private Long merchantId;

    @OneToOne(cascade=CascadeType.ALL,mappedBy = "content")
    private ContentExt contentExt;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Date getSortDate() {
        return sortDate;
    }


    public void setSortDate(Date sortDate) {
        this.sortDate = sortDate;
    }


    public Byte getTopLevel() {
        return topLevel;
    }


    public void setTopLevel(Byte topLevel) {
        this.topLevel = topLevel;
    }


    public Boolean getHasTitleImg() {
        return hasTitleImg;
    }


    public void setHasTitleImg(Boolean hasTitleImg) {
        this.hasTitleImg = hasTitleImg;
    }


    public Boolean getRecommend() {
        return recommend;
    }


    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }


    public Byte getStatus() {
        return status;
    }


    public void setStatus(Byte status) {
        this.status = status;
    }


    public Integer getViewsDay() {
        return viewsDay;
    }


    public void setViewsDay(Integer viewsDay) {
        this.viewsDay = viewsDay;
    }


    public Short getCommentsDay() {
        return commentsDay;
    }


    public void setCommentsDay(Short commentsDay) {
        this.commentsDay = commentsDay;
    }


    public Long getMerchantId() {
        return merchantId;
    }


    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public ContentExt getContentExt() {
        return contentExt;
    }

    public void setContentExt(ContentExt contentExt) {
        this.contentExt = contentExt;
    }
}
