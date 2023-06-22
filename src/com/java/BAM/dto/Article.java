package com.java.BAM.dto;

public class Article extends Dto {

    public String title;
    public String body;
    public int viewCnt;
    public int memberId;

    public Article(int id, String date, int memberId, String title, String body) {
        this.id = id;
        this.date = date;
        this.memberId = memberId;
        this.title = title;
        this.body = body;
        this.viewCnt = 0;
    }

    public void addViewCnt() {
        this.viewCnt++;
    }
}
