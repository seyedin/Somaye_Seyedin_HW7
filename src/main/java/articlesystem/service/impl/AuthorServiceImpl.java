package articlesystem.service.impl;

import articlesystem.model.Article;
import articlesystem.model.Author;
import articlesystem.model.Category;
import articlesystem.model.enums.ArticleStatus;
import articlesystem.repository.ArticleRepository;
import articlesystem.repository.AuthorRepository;
import articlesystem.repository.impl.ArticleRepositoryImpl;
import articlesystem.repository.impl.AuthorRepositoryImpl;
import articlesystem.service.AuthorService;

import java.time.LocalDate;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository = new AuthorRepositoryImpl();
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    @Override
    public List<Author> findAllAuthors(){
        return authorRepository.findAllAuthors();
    }

    @Override
    public void addAuthor(Author author){
        authorRepository.addUserAndAuthor(author);
    }

    @Override
    public void viewArticles(List<Article> articles) {
        int count = 0;
        for (Article article : articles) {
            if (article.isPublished()) {
                count++;
                System.out.println(count + "- " + "Title: " + article.getTitle() + ", Brief: " + article.getBrief());
                System.out.println("Create date: " + article.getCreateDate());
                System.out.println("Publish Date: " + article.getPublishDate());
                if (article.getLastUpdateDate() != null) {
                    System.out.println("Last update date: " + article.getLastUpdateDate());
                }
                System.out.println("======================================");
            }
        }
    }

    @Override
    public void viewArticlesPublishedAfter24HoursAgo(List<Article> articles) {
        LocalDate currentDate = LocalDate.now();
        LocalDate last24HoursLocalDate = currentDate.minusDays(1);
        int count = 0;
        for (Article article : articles) {
            LocalDate articleDate = article.getPublishDate();
            if (article.isPublished() && (articleDate.isEqual(last24HoursLocalDate) || articleDate.isAfter(last24HoursLocalDate))) {
                count++;
                System.out.println(count + "- " + "Title: " + article.getTitle() + ", Brief: " + article.getBrief());
                System.out.println("Create date: " + article.getCreateDate());
                System.out.println("Publish Date: " + article.getPublishDate());
                if (article.getLastUpdateDate() != null) {
                    System.out.println("Last update date: " + article.getLastUpdateDate());
                }
                System.out.println("======================================");
            }
        }
    }

    @Override
    public void viewArticlesPublishedAfterOneWeekAgo(List<Article> articles) {
        LocalDate currentDate = LocalDate.now();
        LocalDate lastOneWeekLocalDate = currentDate.minusWeeks(1);
        int count = 0;
        for (Article article : articles) {
            LocalDate articleDate = article.getPublishDate();
            if (article.isPublished() && (articleDate.isEqual(lastOneWeekLocalDate) || articleDate.isAfter(lastOneWeekLocalDate))) {
                count++;
                System.out.println(count + "- " + "Title: " + article.getTitle() + ", Brief: " + article.getBrief());
                System.out.println("Create date: " + article.getCreateDate());
                System.out.println("Publish Date: " + article.getPublishDate());
                if (article.getLastUpdateDate() != null) {
                    System.out.println("Last update date: " + article.getLastUpdateDate());
                }
                System.out.println("======================================");
            }
        }
    }

    @Override
    public void viewArticlesPublishedAfterOneMonthAgo(List<Article> articles) {
        LocalDate currentDate = LocalDate.now();
        LocalDate lastOneMonthLocalDate = currentDate.minusMonths(1);
        int count = 0;
        for (Article article : articles) {
            LocalDate articleDate = article.getPublishDate();
            if (article.isPublished() && (articleDate.isEqual(lastOneMonthLocalDate) || articleDate.isAfter(lastOneMonthLocalDate))) {
                count++;
                System.out.println(count + "- " + "Title: " + article.getTitle() + ", Brief: " + article.getBrief());
                System.out.println("Create date: " + article.getCreateDate());
                System.out.println("Publish Date: " + article.getPublishDate());
                if (article.getLastUpdateDate() != null) {
                    System.out.println("Last update date: " + article.getLastUpdateDate());
                }
                System.out.println("======================================");
            }
        }
    }

    @Override
    public void viewArticlesPublishedAfterSixMonthsAgo(List<Article> articles) {
        LocalDate currentDate = LocalDate.now();
        LocalDate lastSixMonthsLocalDate = currentDate.minusMonths(6);
        int count = 0;
        for (Article article : articles) {
            LocalDate articleDate = article.getPublishDate();
            if (article.isPublished() && (articleDate.isEqual(lastSixMonthsLocalDate) || articleDate.isAfter(lastSixMonthsLocalDate))) {
                count++;
                System.out.println(count + "- " + "Title: " + article.getTitle() + ", Brief: " + article.getBrief());
                System.out.println("Create date: " + article.getCreateDate());
                System.out.println("Publish Date: " + article.getPublishDate());
                if (article.getLastUpdateDate() != null) {
                    System.out.println("Last update date: " + article.getLastUpdateDate());
                }
                System.out.println("======================================");
            }
        }
    }

    @Override
    public void viewArticlesPublishedAfterLastOneYearAgo(List<Article> articles) {
        LocalDate currentDate = LocalDate.now();
        LocalDate lastOneYearLocalDate = currentDate.minusYears(1);
        int count = 0;
        for (Article article : articles) {
            LocalDate articleDate = article.getPublishDate();
            if (article.isPublished() && (articleDate.isEqual(lastOneYearLocalDate) || articleDate.isAfter(lastOneYearLocalDate))) {
                count++;
                System.out.println(count + "- " + "Title: " + article.getTitle() + ", Brief: " + article.getBrief());
                System.out.println("Create date: " + article.getCreateDate());
                System.out.println("Publish Date: " + article.getPublishDate());
                if (article.getLastUpdateDate() != null) {
                    System.out.println("Last update date: " + article.getLastUpdateDate());
                }
                System.out.println("======================================");
            }
        }
    }

    @Override
    public void viewArticlesPublishedByCategory(Category category) {
        int categoryId = category.getId();
        List<Article> articleList = articleRepository.findArticleByCategoryId(categoryId);
        for (Article article : articleList) {
            System.out.println("Title: " + article.getTitle() + ", Brief:  " + article.getBrief() + ", Content: " + article.getContent());
        }
    }

    @Override
    public void editArticle(Article article, String newTitle, String newBrief, String newContent) {
        article.setTitle(newTitle);
        article.setBrief(newBrief);
        article.setContent(newContent);
        article.setLastUpdateDate(LocalDate.now());
        System.out.println("Article updated");
    }

    @Override
    public void submitArticle(Article article) {
        article.setPublished(false);
        article.setStatus(ArticleStatus.PENDING);
    }
}
