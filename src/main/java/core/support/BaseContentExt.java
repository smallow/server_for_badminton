package core.support;

import com.springapp.mvc.test.Content;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by smallow on 2015/4/28.
 */
@Deprecated
public abstract class BaseContentExt  implements Serializable {


    // constructors
    public BaseContentExt () {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseContentExt (java.lang.Integer id) {
        this.setId(id);
        initialize();
    }
    protected void initialize () {}
    // primary key
    private java.lang.Integer id;

    // fields
    private java.lang.String title;
    private java.lang.String shortTitle;
    private java.lang.String description;
    private java.lang.String author;
    private java.util.Date releaseDate;
    private java.lang.String titleImg;

    // one to one
    private Content content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
