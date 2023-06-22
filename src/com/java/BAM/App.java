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
            System.out.println();
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

            String actionName = controllerName + "/" + methodName;
            switch (actionName) {
                case "article/write" :
                case "article/modify" :
                case "article/delete" :
                case "member/profile" :
                case "member/logout" :
                    if (!Controller.isLogined()) {
                        System.out.println("please Login");
                        continue;
                    }
                    break;
                case "member/join" :
                case "member/login" :
                    if (Controller.isLogined()) {
                        System.out.println("please Logout");
                        continue;
                    }
                    break;
            }

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
        }

        System.out.println("== 프로그램 끝 ==");

        sc.close();
    }


}
