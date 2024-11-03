package articlesystem.service.impl;

import articlesystem.model.Article;
import articlesystem.repository.ArticleRepository;
import articlesystem.repository.impl.ArticleRepositoryImpl;
import articlesystem.service.ArticleService;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    @Override
    public void addArticle(Article article, int authorId, int categoryId) {
        articleRepository.addArticle(article, authorId, categoryId);
    }

    @Override
    public List<Article> getAllArticlesByUserId(int authorId) {
        return articleRepository.findAllArticlesByAuthorId(authorId);
    }

    @Override
    public List<Article> findAllArticlesByAuthorId(int authorId) {
        return articleRepository.findAllArticlesByAuthorId(authorId);

    }

    @Override
    public Article findArticleById(int id) {
      return   articleRepository.findArticleById(id);
    }

    @Override
    public List<Article> findAllPendingArticles() {
        return articleRepository.findAllPendingArticles();
    }

    @Override
    public boolean updateArticle(int articleId, String newTitle, String newBrief, String newContent) {
        return articleRepository.updateArticle(articleId, newTitle, newBrief, newContent);
    }

}
