package com.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 프로그램 시작 ==");

        Scanner sc = new Scanner(System.in);

        int articleId = 0;

        List<Article> list = new ArrayList<>();

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

            if (cmd.equals("list")) {
                if (list.isEmpty()) {
                    System.out.println("no list");
                } else {
                    System.out.printf("\tID\t\tTITLE\n");
                    for (int i = 0; i < list.size(); i++) {
                        Article article = list.get(list.size() - 1 - i);
                        System.out.printf("\t%d\t\t%s\n", article.id, article.title);
                    }
                }
            } else if (cmd.equals("write")) {
                int id = ++articleId;
                System.out.print("title)");
                String title = sc.nextLine();
                System.out.print("body)");
                String body = sc.nextLine();

                list.add(new Article(id, title, body));

                System.out.printf("save to %dth list\n", id);
            } else if (cmd.startsWith("detail ")) {
                String[] arr = cmd.split(" ");
                int id = Integer.parseInt(arr[1]);
                Article foundArticle = null;

                for (int i = 0; i < list.size(); i++) {
                    Article article = list.get(i);
                    if (article.id == id) {
                        foundArticle = article;
                    }
                }

                if (foundArticle == null) {
                    System.out.printf("not found list : %d", id);
                } else {
                    System.out.printf("ID : %d\n", foundArticle.id);
                    System.out.printf("TITLE : %s\n", foundArticle.title);
                    System.out.printf("BODY : %s\n", foundArticle.body);
                }
            } else {
                System.out.println("not cmd");
            }

            System.out.println();
        }



        System.out.println("== 프로그램 끝 ==");

        sc.close();
    }
}

class Article {
    int id;
    String title;
    String body;

    public Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}