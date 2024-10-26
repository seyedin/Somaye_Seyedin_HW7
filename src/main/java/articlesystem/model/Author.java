package articlesystem.model;

import articlesystem.model.enums.ArticleStatus;

import java.util.Date;
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
                System.out.println(count+"- " + "Title: " + article.getTitle() + ", Brief: " + article.getBrief());
                System.out.println("Create date: " + article.getCreateDate());
                System.out.println("Publish Date: " + article.getPublishDate());
                if (article.getLastUpdateDate() != null) {
                    System.out.println("Last update date: " + article.getLastUpdateDate());
                }
                System.out.println("======================================");
            }
        }
    }

    public void editArticle(Article article, String newTitle, String newBrief, String newContent) {
        article.setTitle(newTitle);
        article.setBrief(newBrief);
        article.setContent(newContent);
        article.setLastUpdateDate(new Date());
        System.out.println("Article updated");
    }

    public void submitArticle(Article article) {
        article.setPublished(false);
        article.setStatus(ArticleStatus.PENDING);
        System.out.println("Article submitted");
    }
}