package com.java.BAM.controller;

import com.java.BAM.dto.Member;

public abstract class Controller {

    public static Member loginedMember;
    public abstract void doAction(String cmd, String methodName);

    public abstract void makeTestData();

    public static boolean isLogined() {
        return loginedMember != null;
    }
}
