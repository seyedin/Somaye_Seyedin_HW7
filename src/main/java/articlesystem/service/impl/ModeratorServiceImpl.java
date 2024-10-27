package articlesystem.service.impl;

import articlesystem.model.Article;
import articlesystem.model.Moderator;
import articlesystem.model.enums.ArticleStatus;
import articlesystem.repository.ModeratorRepository;
import articlesystem.repository.impl.ModeratorRepositoryImpl;
import articlesystem.service.ModeratorService;

import java.time.LocalDate;
import java.util.List;

public class ModeratorServiceImpl implements ModeratorService {
    private final ModeratorRepository moderatorRepository = new ModeratorRepositoryImpl();

    @Override
    public List<Moderator> findAllModerators() {
        return moderatorRepository.findAllModerators();
    }

    @Override
    public void addModerator(Moderator moderator) {
        moderatorRepository.addModerator(moderator);
    }

    @Override
    public void approveArticle(Article article) {
        if (article != null) {
            article.setStatus(ArticleStatus.APPROVED);
            article.setPublished(true);
            article.setPublishDate(LocalDate.now());
            System.out.println("Article approved");
        }
    }

    @Override
    public void rejectArticle(Article article) {
        if (article != null) {
            article.setStatus(ArticleStatus.REJECTED);
            System.out.println("Article rejected");
        }
    }
}
