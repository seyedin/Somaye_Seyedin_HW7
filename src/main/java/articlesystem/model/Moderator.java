package articlesystem.model;

import articlesystem.model.enums.ArticleStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Moderator extends User {
    public Moderator(int id, String username, String password) {
        super(id, username, password);
    }

    public void approveArticle(Article article) {
        if (article != null) {
            article.setStatus(ArticleStatus.APPROVED);
            article.setPublished(true);
            article.setPublishDate(LocalDate.now());
            System.out.println("Article approved");
        }
    }

    public void rejectArticle(Article article) {
        if (article != null) {
            article.setStatus(ArticleStatus.REJECTED);
            System.out.println("Article rejected");
        }
    }
}