package com.java.BAM;

import com.java.BAM.controller.ArticleController;
import com.java.BAM.controller.Controller;
import com.java.BAM.controller.MemberController;

import java.util.Scanner;

public class App {

    private static Scanner sc = new Scanner(System.in);

    public static void run() {
        System.out.println("== 프로그램 시작 ==");

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        articleController.makeTestData();
        memberController.makeTestData();

        while (true) {
            System.out.print("cmd)");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("please write cmd");
                continue;
            }

            if (cmd.equals("exit")) {
                break;
            }

            String[] cmdBits = cmd.split(" ");

            if (cmdBits.length == 1) {
                System.out.println("not cmd");
                continue;
            }

            String controllerName = cmdBits[0];
            String methodName = cmdBits[1];

            Controller controller = null;

            if (controllerName.equals("member")) {
                controller = memberController;
            } else if (controllerName.equals("article")) {
                controller = articleController;
            }else {
                System.out.println("not cmd");
                continue;
            }

            controller.doAction(cmd, methodName);

            System.out.println();



            /*//============================================================
            // 회원 가입
            if (cmd.equals("member join")) {
                memberController.doJoin();
            }
            //============================================================
            // 게시글 검색
            else if (cmd.startsWith("article list")) {
                articleController.showList();
                //============================================================
                // 게시글 작성
            } else if (cmd.equals("article write")) {
                articleController.doWrite();
                //============================================================
                // 게시글 열람
            } else if (cmd.startsWith("article detail ")) {
                articleController.doDetail();
                //============================================================
                // 게시글 삭제
            } else if (cmd.startsWith("article delete ")) {
                articleController.doDelete();
                //============================================================
                // 게시글 수정
            } else if (cmd.startsWith("article modify ")) {
                articleController.doModify();
                //============================================================
            } else {
                System.out.println("not cmd");
            }

            System.out.println();*/
        }

        System.out.println("== 프로그램 끝 ==");

        sc.close();
    }


}
