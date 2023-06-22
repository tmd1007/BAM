package com.java.BAM.service;

import com.java.BAM.container.Container;
import com.java.BAM.dao.ArticleDao;
import com.java.BAM.dto.Article;

import java.util.List;

public class ArticleService {

    private ArticleDao articleDao;

    public ArticleService() {
        this.articleDao = Container.articleDao;
    }

    public List<Article> getPrintArticles(String searchKey) {
        return articleDao.getPrintArticles(searchKey);
    }

    public int getLastId() {
        return articleDao.getLastId();
    }

    public void add(Article article) {
        articleDao.add(article);
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public void remove(Article foundArticle) {
        articleDao.remove(foundArticle);
    }
}
