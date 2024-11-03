package articlesystem.service;

import articlesystem.model.Article;
import articlesystem.model.Author;
import articlesystem.model.Category;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthors();

    void addAuthor(Author author);

    void viewArticles(List<Article> articles);

    void viewArticlesPublishedAfter24HoursAgo(List<Article> articles);

    void viewArticlesPublishedAfterOneWeekAgo(List<Article> articles);

    void viewArticlesPublishedAfterOneMonthAgo(List<Article> articles);

    void viewArticlesPublishedAfterSixMonthsAgo(List<Article> articles);

    void viewArticlesPublishedAfterLastOneYearAgo(List<Article> articles);

    void viewArticlesPublishedByCategory(Category category);

    void editArticle(Article article, String newTitle, String newBrief, String newContent);

    void submitArticle(Article article);
}
