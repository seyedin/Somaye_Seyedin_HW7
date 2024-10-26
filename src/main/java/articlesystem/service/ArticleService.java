package articlesystem.service;

import articlesystem.model.Article;

import java.util.List;

public interface ArticleService {
    void addArticle(Article article);

    List<Article> getPublishedArticles();

    List<Article> findAllArticles();

    Article findArticleById(int id);
}
