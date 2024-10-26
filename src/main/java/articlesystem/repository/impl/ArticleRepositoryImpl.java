package articlesystem.repository.impl;

import articlesystem.database.Database;
import articlesystem.model.Article;
import articlesystem.repository.ArticleRepository;

import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {
    @Override
    public List<Article> findAllArticles() {
        return Database.articles;
    }

    @Override
    public void addArticle(Article article) {
        Database.articles.add(article);
    }

    @Override
    public Article findArticleById(int id) {
        for (Article article : Database.articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }
}
