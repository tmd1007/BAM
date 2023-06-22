package com.java.BAM.controller;

import com.java.BAM.Util;
import com.java.BAM.container.Container;
import com.java.BAM.dto.Article;
import com.java.BAM.service.ArticleService;
import com.java.BAM.service.MemberService;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ArticleController  extends Controller {

    private Scanner sc;
    private String cmd;
    private ArticleService articleService;
    private MemberService memberService;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        this.articleService = Container.articleService;
        this.memberService = Container.memberService;
    }

    @Override
    public void doAction(String cmd, String methodName) {
        this.cmd = cmd;

        switch (methodName) {
            case "write" :
                doWrite();
                break;
            case "list" :
                showList();
                break;
            case "detail" :
                doDetail();
                break;
            case "delete" :
                doDelete();
                break;
            case "modify" :
                doModify();
                break;
            default:
                System.out.println("not cmd");
        }
    }

    private void doWrite() {

        int id = articleService.getLastId();
        System.out.print("title)");
        String title = sc.nextLine();
        System.out.print("body)");
        String body = sc.nextLine();

        Article article = new Article(id, Util.getDate(), loginedMember.id, title, body);
        Container.articleService.add(article);
        //list.add(new Article(id, Util.getDate(), loginedMember.id, title, body));

        System.out.printf("save to %dth list\n", id);
    }

    private void showList() {
        String searchKey = cmd.substring("article list".length()).trim();

        System.out.printf("Keyword : %s\n", searchKey);

        List<Article> printArticles = articleService.getPrintArticles(searchKey);

        if (printArticles.isEmpty()) {
            System.out.println("no list");
            return;
        }

        Collections.reverse(printArticles);
        System.out.print("\tListNum\t\tTITLE\t\tDATE\t\t\tVIEW\t\tNAME\n");
        for (Article article : printArticles) {
            String writerName = memberService.getWriterName(article.memberId);

            System.out.printf("\t%d\t\t\t%s\t\t%s\t%d\t\t\t%s\n",
                    article.id, article.title, article.date, article.viewCnt, writerName);
        }
    }

    private void doDetail() {
        String[] arr = cmd.split(" ");
        if (arr.length != 3 || Character.isDigit(Integer.parseInt(arr[2]))) {
            System.out.println("잘못된 명령어");
            return;
        }
        int id = Integer.parseInt(arr[arr.length-1]);
        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null) {
            System.out.printf("not found list : %d", id);
        } else {
            foundArticle.addViewCnt();

            String writerName = memberService.getWriterName(foundArticle.id);

            System.out.printf("ListNum : %d\n", foundArticle.id);
            System.out.printf("DATE : %s\n", foundArticle.date);
            System.out.printf("WRITER : %s\n", writerName);
            System.out.printf("TITLE : %s\n", foundArticle.title);
            System.out.printf("BODY : %s\n", foundArticle.body);
            System.out.printf("VIEW : %d\n", foundArticle.viewCnt);
        }
    }

    private void doDelete() {
        String[] arr = cmd.split(" ");
        int id = Integer.parseInt(arr[arr.length-1]);
        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null) {
            System.out.printf("not found list : %d\n", id);
        } else {
            if (foundArticle.memberId != loginedMember.id) {
                System.out.println("권한이 없습니다.");
                return;
            }
            //Container.articleDao.articles.remove(foundArticle);
            articleService.remove(foundArticle);
            System.out.printf("delete list : %d\n", id);
        }
    }

    private void doModify() {
        String[] arr = cmd.split(" ");
        int id = Integer.parseInt(arr[arr.length-1]);
        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null) {
            System.out.printf("not found list : %d\n", id);
        } else {
            if (foundArticle.memberId != loginedMember.id) {
                System.out.println("권한이 없습니다.");
                return;
            }

            System.out.print("modify title)");
            String title = sc.nextLine();
            System.out.print("modify body)");
            String body = sc.nextLine();
            foundArticle.title = title;
            foundArticle.body = body;
            System.out.printf("success modify : %d\n", foundArticle.id);
        }
    }

    public void makeTestData() {
        for (int i = 1; i <= 3; i++) {
            int id = articleService.getLastId();
            String title = "title" + i;
            String body = "body" + i;
            Article article = new Article(id, Util.getDate(), i, title, body);
            article.viewCnt = i * 10;
            articleService.add(article);
        }
        System.out.println("testdata update");
    }
}