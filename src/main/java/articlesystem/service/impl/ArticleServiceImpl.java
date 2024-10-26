package articlesystem.service.impl;

import articlesystem.model.Article;
import articlesystem.repository.ArticleRepository;
import articlesystem.repository.impl.ArticleRepositoryImpl;
import articlesystem.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    @Override
    public void addArticle(Article article) {
        articleRepository.addArticle(article);
    }

    @Override
    public List<Article> getPublishedArticles() {
        List<Article> publishedArticles = new ArrayList<>();
        List<Article> allArticles = articleRepository.findAllArticles();
        for (Article article : allArticles) {
            if (article.isPublished()) {
                publishedArticles.add(article);
            }
        }
        return publishedArticles;
    }

    @Override
    public List<Article> findAllArticles() {
     return   articleRepository.findAllArticles();

    }

    @Override
    public Article findArticleById(int id) {
      return   articleRepository.findArticleById(id);
    }
}
