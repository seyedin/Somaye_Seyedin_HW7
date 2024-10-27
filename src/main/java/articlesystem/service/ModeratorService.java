package articlesystem.service;

import articlesystem.model.Article;
import articlesystem.model.Moderator;

import java.util.List;

public interface ModeratorService {

    List<Moderator> findAllModerators();
    void addModerator(Moderator moderator);

    void approveArticle(Article article);

    void rejectArticle(Article article);
}
