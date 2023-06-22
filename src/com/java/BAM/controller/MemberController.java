package com.java.BAM.controller;

import com.java.BAM.Util;
import com.java.BAM.container.Container;
import com.java.BAM.dto.Member;
import com.java.BAM.service.MemberService;

import java.util.Scanner;

public class MemberController extends Controller {

    private Scanner sc;
    private MemberService memberService;

    public MemberController(Scanner sc) {
        this.sc = sc;
        loginedMember = null;
        this.memberService  = Container.memberService;
    }

    @Override
    public void doAction(String cmd, String methodName) {

        switch (methodName) {
            case "join" :
                doJoin(); break;
            case "login" :
                doLogin(); break;
            case "logout" :
                doLogout(); break;
            case "profile" :
                showProfile(); break;
            default:
                System.out.println("not cmd");
        }
    }

    private void doJoin() {

        int id = memberService.getLastId();
        String loginId = null;
        while(true) {
            System.out.print("ID)");
            loginId = sc.nextLine();
            if (StringZeroChk(loginId)) {
                continue;
            }
            if (!Container.memberService.loginIdChk(loginId)) {
                continue;
            }
            break;
        }

        String loginPw = null;
        String loginPwChk = null;
        while(true) {
            System.out.print("PW)");
            loginPw = sc.nextLine();
            if (StringZeroChk(loginPw)) {
                continue;
            }
            System.out.print("PWChk)");
            loginPwChk = sc.nextLine();
            if (StringZeroChk(loginPwChk)) {
                continue;
            }
            if (!loginPw.equals(loginPwChk)) {
                System.out.println("비밀번호 불일치");
                continue;
            }
            break;
        }
        String name = null;
        while(true) {
            System.out.print("name)");
            name = sc.nextLine();
            if (StringZeroChk(name)) {
                continue;
            }
            break;
        }

        Member member = new Member(id, Util.getDate(), loginId, loginPw, name);
        memberService.add(member);

        System.out.printf("%d번 회원 생성 완료\n", id);
    }

    private void doLogin() {

        System.out.print("login ID : ");
        String loginId = sc.nextLine();

        Member member = memberService.getMemberByLoginId(loginId);

        System.out.print("login PW : ");
        String loginPw = sc.nextLine();

        if (member == null) {
            System.out.println("not exist ID");
            return;
        }

        if (!member.loginPw.equals(loginPw)) {
            System.out.println("Mismatch PW");
            return;
        }

        loginedMember = member;
        System.out.printf("success Login : %s", member.loginId);
    }

    private void doLogout() {

        System.out.printf("success Logout : %s", loginedMember.loginId);
        loginedMember = null;
    }



    private void showProfile() {

        System.out.println("==My profile==");
        System.out.printf("ID : %s\n", loginedMember.loginId);
        System.out.printf("Name : %s\n", loginedMember.name);
        System.out.printf("Num : %s\n", loginedMember.id);
        System.out.printf("Date : %s\n", loginedMember.date);
    }



    private boolean StringZeroChk(String s) {
        if (s.length() == 0) {
            System.out.println("입력된 정보가 없습니다.");
            return true;
        }
        return false;
    }



    //int id, String date, String loginId, String loginPw, String name
    public void makeTestData() {
        for (int i = 1; i <= 3; i++) {
            int id = memberService.getLastId();
            String ID = "test" + i;
            String PW = "test" + i;
            String name = "test" + i;
            Member member = new Member(id, Util.getDate(), ID, PW, name);
            memberService.add(member);
        }
        System.out.println("Logindata update");
    }
}
