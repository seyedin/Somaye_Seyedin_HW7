package articlesystem.repository;

import articlesystem.model.Article;
import articlesystem.model.enums.ArticleStatus;

import java.util.List;

public interface ArticleRepository {

    List<Article> findAllArticlesByAuthorId(int authorId);

    void addArticle(Article article, int authorId, int categoryId);

    Article findArticleById(int id);

    List<Article> findArticleByCategoryId(int categoryId);

    List<Article> findAllPendingArticles();

    boolean updateArticle(int articleId, String newTitle, String newBrief, String newContent);

    boolean approveOrRejectArticle(int articleId, boolean isPublished, ArticleStatus status);
}
