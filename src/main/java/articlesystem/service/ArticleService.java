package articlesystem.service;

import articlesystem.model.Article;

import java.util.List;

public interface ArticleService {

    void addArticle(Article article, int authorId, int categoryId);

    List<Article> getAllArticlesByUserId(int authorId);

    List<Article> findAllArticlesByAuthorId(int authorId);
    Article findArticleById(int id);

    List<Article> findAllPendingArticles();

    boolean updateArticle(int articleId, String newTitle, String newBrief, String newContent);

}
