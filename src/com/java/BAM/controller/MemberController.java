package com.java.BAM.controller;

import com.java.BAM.Util;
import com.java.BAM.dto.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {

    private int memberId;
    private List<Member> members;
    private Scanner sc;
    private Member loginedMember;

    public MemberController(Scanner sc) {
        this.memberId = 0;
        this.members = new ArrayList<>();
        this.sc = sc;
        this.loginedMember = null;
    }

    @Override
    public void doAction(String cmd, String methodName) {

        switch (methodName) {
            case "join" : doJoin(); break;
            case "login" : doLogin(); break;
            case "logout" : doLogout(); break;
            case "profile" : showProfile(); break;
            default:
                System.out.println("not cmd");
        }
    }

    private void doJoin() {

        if (isLogined()) {
            System.out.println("please Logout");
            return;
        }

        int id = ++memberId;
        String loginId = null;
        while(true) {
            System.out.print("ID)");
            loginId = sc.nextLine();
            if (StringZeroChk(loginId)) {
                continue;
            }
            if (!loginIdChk(loginId)) {
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
        System.out.print("name)");
        String name = sc.nextLine();

        members.add(new Member(id, Util.getDate(), loginId, loginPw, name));
        System.out.printf("%d번 회원 생성 완료\n", id);
    }

    private void doLogin() {

        if (isLogined()) {
            System.out.println("error : already logged in");
            return;
        }

        System.out.printf("login ID : ");
        String loginId = sc.nextLine();

        Member member = getMemberByLoginId(loginId);

        System.out.printf("login PW : ");
        String loginPw = sc.nextLine();

        if (member == null) {
            System.out.println("not exist ID");
            return;
        }

        if (!member.loginPw.equals(loginPw)) {
            System.out.println("Mismatch PW");
            return;
        }

        this.loginedMember = member;
        System.out.printf("success Login : %s", member.loginId);
    }

    private void doLogout() {
        if (!isLogined()) {
            System.out.println("error : not logged in");
            return;
        }

        System.out.printf("success Logout : %s", loginedMember.loginId);
        this.loginedMember = null;
    }

    private boolean isLogined() {
        return loginedMember != null;
    }

    private void showProfile() {
        if (!isLogined()) {
            System.out.println("error : not logged in");
            return;
        }
        System.out.println("==My profile==");
        System.out.printf("ID : %s\n", loginedMember.loginId);
        System.out.printf("Name : %s\n", loginedMember.name);
        System.out.printf("Num : %s\n", loginedMember.id);
        System.out.printf("Date : %s\n", loginedMember.date);
    }

    private Member getMemberByLoginId(String loginId) {
        for (Member m : members) {
            if (m.loginId.equals(loginId)) {
                return m;
            }
        }

        return null;
    }

    private boolean StringZeroChk(String s) {
        if (s.length() == 0) {
            System.out.println("입력된 정보가 없습니다.");
            return true;
        }
        return false;
    }

    private boolean loginIdChk(String id) {

        Member member = getMemberByLoginId(id);

        if (member != null) {
            return false;
        }

        return true;
    }

    //int id, String date, String loginId, String loginPw, String name
    public void makeTestData() {
        for (int i = 1; i <= 3; i++) {
            int id = ++memberId;
            String ID = "test" + i;
            String PW = "test" + i;
            String name = "test" + i;
            Member member = new Member(id, Util.getDate(), ID, PW, name);
            members.add(member);
        }
        System.out.println("Logindata update");
    }
}
