package com.es.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
@Document(indexName = "blog22",type = "article")
public class Article {
    @Id
    @Field(type = FieldType.Long, store = true)
    private long id;
    @Field(type = FieldType.keyword, store = true)
    private String title;
    @Field(type = FieldType.keyword, store = true)
    private String content;
    @Field(type = FieldType.Integer,store = true)
    private int searchNo;
    @Field(type = FieldType.Long,store = true)
    private long cnt;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Field(type = FieldType.keyword,store = true)
    private String mark;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSearchNo() {
        return searchNo;
    }

    public void setSearchNo(int searchNo) {
        this.searchNo = searchNo;
    }

    public long getCnt() {
        return cnt;
    }

    public void setCnt(long cnt) {
        this.cnt = cnt;
    }
}
