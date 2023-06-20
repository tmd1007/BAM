package com.java.BAM.dto;

public class Member extends Dto{

    public String loginId;
    public String loginPw;
    public String name;

    public Member(int id, String date, String loginId, String loginPw, String name) {
        this.id = id;
        this.date = date;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }
}
