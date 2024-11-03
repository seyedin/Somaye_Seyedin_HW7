package articlesystem.service.impl;

import articlesystem.model.Article;
import articlesystem.model.Moderator;
import articlesystem.model.enums.ArticleStatus;
import articlesystem.repository.ArticleRepository;
import articlesystem.repository.ModeratorRepository;
import articlesystem.repository.impl.ArticleRepositoryImpl;
import articlesystem.repository.impl.ModeratorRepositoryImpl;
import articlesystem.service.ModeratorService;

import java.util.List;

public class ModeratorServiceImpl implements ModeratorService {
    private final ModeratorRepository moderatorRepository = new ModeratorRepositoryImpl();
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    @Override
    public List<Moderator> findAllModerators() {
        return moderatorRepository.findAllModerators();
    }

    @Override
    public void approveArticle(Article article) {
        if (article != null) {
            articleRepository.approveOrRejectArticle(article.getId(), true, ArticleStatus.APPROVED);
            System.out.println("Article approved");
        }
    }

    @Override
    public void rejectArticle(Article article) {
        if (article != null) {
            articleRepository.approveOrRejectArticle(article.getId(), false, ArticleStatus.REJECTED);
            System.out.println("Article rejected");
        }
    }
}
