package articlesystem.model;

import articlesystem.model.enums.ArticleStatus;

import java.time.LocalDate;
import java.util.List;

public class Author extends User {
    private String nationalCode;
    private String birthday;

    public Author(int id, String username, String nationalCode, String birthday, String password) {
        super(id, username, password);
        this.nationalCode = nationalCode;
        this.birthday = birthday;
    }

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

    public void viewArticlesPublishedByCategory(List<Article> articles, Category category) {
        int count = 0;
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getCategory().equals(category) && articles.get(i).isPublished()) {
                count++;
                System.out.println(count + "- " + "Title: " + articles.get(i).getTitle() + ", Brief: " + articles.get(i).getBrief() + ", Content: " + articles.get(i).getContent());
                System.out.println("======================================");
            }
        }
    }

    public void editArticle(Article article, String newTitle, String newBrief, String newContent) {
        article.setTitle(newTitle);
        article.setBrief(newBrief);
        article.setContent(newContent);
        article.setLastUpdateDate(LocalDate.now());
        System.out.println("Article updated");
    }

    public void submitArticle(Article article) {
        article.setPublished(false);
        article.setStatus(ArticleStatus.PENDING);
        System.out.println("Article submitted");
    }
}