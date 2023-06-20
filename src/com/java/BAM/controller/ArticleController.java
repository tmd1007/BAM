package com.java.BAM.controller;

import com.java.BAM.Util;
import com.java.BAM.dto.Article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ArticleController  extends Controller {

    private int articleId;
    private List<Article> list;
    private Scanner sc;
    private String cmd;

    public ArticleController(Scanner sc) {
        this.articleId = 0;
        this.list = new ArrayList<>();
        this.sc = sc;
    }

    @Override
    public void doAction(String cmd, String methodName) {
        this.cmd = cmd;

        switch (methodName) {
            case "write" : doWrite(); break;
            case "list" : showList(); break;
            case "detail" : doDetail(); break;
            case "delete" : doDelete(); break;
            case "modify" : doModify(); break;
            default:
                System.out.println("not cmd");
        }
    }

    private void doWrite() {
        int id = ++articleId;
        System.out.print("title)");
        String title = sc.nextLine();
        System.out.print("body)");
        String body = sc.nextLine();

        list.add(new Article(id, Util.getDate(), title, body));

        System.out.printf("save to %dth list\n", id);
    }

    private void showList() {
        String searchKey = cmd.substring("article list".length()).trim();

        List<Article> printList = new ArrayList<>(list);

        if (searchKey.length() != 0) {
            printList.clear();
            for (Article article : list) {
                if (article.title.contains(searchKey)) {
                    printList.add(article);
                }
            }
            if (printList.isEmpty()) {
                System.out.println("no list");
                return;
            }
        }

        Collections.reverse(printList);
        System.out.printf("\tID\t\tTITLE\t\tDATE\t\t\tVIEW\n");
        for (Article article : printList) {
            System.out.printf("\t%d\t\t%s\t\t%s\t\t%d\n",
                    article.id, article.title, article.date, article.viewCnt);
        }
    }

    private void doDetail() {
        String[] arr = cmd.split(" ");
        int id = Integer.parseInt(arr[arr.length-1]);
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
    }

    private void doDelete() {
        String[] arr = cmd.split(" ");
        int id = Integer.parseInt(arr[arr.length-1]);
        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            System.out.printf("not found list : %d\n", id);
        } else {
            list.remove(foundArticle);
            System.out.printf("delete list : %d\n", id);
        }
    }

    private void doModify() {
        String[] arr = cmd.split(" ");
        int id = Integer.parseInt(arr[arr.length-1]);
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
    }

    private Article getArticleById(int id) {
        for (Article article : list) {
            if (article.id == id) {
                return article;
            }
        }

        return null;
    }

    public void makeTestData() {
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
