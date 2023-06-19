package com.java.BAM;

import com.java.BAM.dto.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static List<Article> list;
    private static int articleId;

    App() {
        list = new ArrayList<>();
    }

    public static void run() {
        System.out.println("== 프로그램 시작 ==");

        Scanner sc = new Scanner(System.in);

        makeTestData();

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
                    System.out.printf("\tID\t\tTITLE\t\tDATE\t\t\tVIEW\n");
                    for (int i = 0; i < list.size(); i++) {
                        Article article = list.get(list.size() - 1 - i);
                        System.out.printf("\t%d\t\t%s\t\t%s\t\t%d\n",
                                article.id, article.title, article.date, article.viewCnt);
                    }
                }
            } else if (cmd.equals("write")) {
                int id = ++articleId;
                System.out.print("title)");
                String title = sc.nextLine();
                System.out.print("body)");
                String body = sc.nextLine();

                list.add(new Article(id, Util.getDate(), title, body));

                System.out.printf("save to %dth list\n", id);
            } else if (cmd.startsWith("detail ")) {
                String[] arr = cmd.split(" ");
                int id = Integer.parseInt(arr[1]);
                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.printf("not found list : %d", id);
                } else {
                    foundArticle.addViewCnt();

                    System.out.printf("ID : %d\n", foundArticle.id);
                    System.out.printf("DATE : %s\n", foundArticle.date);
                    System.out.printf("TITLE : %s\n", foundArticle.title);
                    System.out.printf("BODY : %s\n", foundArticle.body);
                    System.out.printf("VIEW : %d\n", foundArticle.viewCnt);
                }
            } else if (cmd.startsWith("delete ")) {
                String[] arr = cmd.split(" ");
                int id = Integer.parseInt(arr[1]);
                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.printf("not found list : %d\n", id);
                } else {
                    list.remove(foundArticle);
                    System.out.printf("delete list : %d\n", id);
                }
            } else if (cmd.startsWith("modify ")) {
                String[] arr = cmd.split(" ");
                int id = Integer.parseInt(arr[1]);
                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.printf("not found list : %d\n", id);
                } else {
                    System.out.printf("modify title)");
                    String title = sc.nextLine();
                    System.out.printf("modify body)");
                    String body = sc.nextLine();
                    foundArticle.title = title;
                    foundArticle.body = body;
                    System.out.printf("success modify : %d\n", foundArticle.id);
                }
            } else {
                System.out.println("not cmd");
            }

            System.out.println();
        }

        System.out.println("== 프로그램 끝 ==");

        sc.close();
    }

/*    private static int getArticleIdxById(int id) {
        int i = 0;
        for (Article article : list) {
            if (article.id == id) {
                return i;
            }
            i++;
        }

        return -1;
    }*/

    private static Article getArticleById(int id) {
        for (Article article : list) {
            if (article.id == id) {
                return article;
            }
        }

        return null;
    }

    private static void makeTestData() {
        for (int i = 1; i <= 3; i++) {
            String title = "title" + i;
            String body = "body" + i;
            Article article = new Article(++articleId, Util.getDate(), title, body);
            article.viewCnt = i * 10;
            list.add(article);
        }
        System.out.println("testdata update");
    }
}
