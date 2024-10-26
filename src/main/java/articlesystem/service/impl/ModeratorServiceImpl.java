package articlesystem.service.impl;

import articlesystem.model.Moderator;
import articlesystem.repository.ModeratorRepository;
import articlesystem.repository.impl.ModeratorRepositoryImpl;
import articlesystem.service.ModeratorService;

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
}
