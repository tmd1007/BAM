package com.java.BAM.dto;

public class Article {
    public int id;
    public String date;
    public String title;
    public String body;
    public int viewCnt;

    public Article(int id, String date, String title, String body) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.body = body;
        this.viewCnt = 0;
    }

    public void addViewCnt() {
        this.viewCnt++;
    }
}
