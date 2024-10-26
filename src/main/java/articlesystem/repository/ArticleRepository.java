package articlesystem.repository;

import articlesystem.model.Article;

import java.util.List;

public interface ArticleRepository {
    List<Article> findAllArticles();
    void addArticle(Article article);

    Article findArticleById(int id);
}
