package com.springapp.mvc.test;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by smallow on 2015/4/28.
 */
@Entity
@Table(name="tb_content_ext")
public class ContentExt{


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private java.lang.Integer id;
    @Column(name = "title")
    private java.lang.String title;
    @Column(name = "short_title")
    private java.lang.String shortTitle;
    @Column(name = "description")
    private java.lang.String description;
    @Column(name = "author")
    private java.lang.String author;
    @Column(name = "release_date")
    private java.util.Date releaseDate;
    @Column(name = "title_img")
    private java.lang.String titleImg;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id",referencedColumnName="id")
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
