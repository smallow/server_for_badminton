package core.support;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by smallow on 2015/4/28.
 */
@Deprecated
public abstract class BaseContent implements Serializable {


    private int hashCode = Integer.MIN_VALUE;

    public BaseContent() {
        initialize();
    }
    public BaseContent(Integer id) {
        this.setId(id);
        initialize();
    }
    protected void initialize() {
    }


    private Integer id;
    private Date sortDate;
    private Byte topLevel;
    private Boolean hasTitleImg;
    private Boolean recommend;
    private Byte status;
    private Integer viewsDay;
    private Short commentsDay;
    private Long merchantId;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private BaseContentExt contentExt;

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
}
