package com.java.BAM.dao;

import com.java.BAM.container.Container;
import com.java.BAM.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends Dao{
    private List<Article> articles;

    public ArticleDao() {
        this.articles = new ArrayList<>();
    }

    public void add(Article article) {
        articles.add(article);
        lastId++;
    }

    public List<Article> getPrintArticles(String searchKey) {
        if (searchKey != null) {
            List<Article> printArticles = new ArrayList<>();
            for (Article article : Container.articleDao.articles) {
                if (article.title.contains(searchKey)) {
                    printArticles.add(article);
                }
            }
            return printArticles;
        }

        return articles;
    }

    public void remove(Article foundArticle) {
        articles.remove(foundArticle);
    }

    public Article getArticleById(int id) {
        for (Article article : articles) {
            if (article.id == id) {
                return article;
            }
        }
        return null;
    }
}