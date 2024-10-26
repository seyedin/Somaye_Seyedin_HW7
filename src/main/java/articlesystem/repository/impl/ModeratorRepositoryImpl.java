package articlesystem.repository.impl;

import articlesystem.database.Database;
import articlesystem.model.Moderator;
import articlesystem.repository.ModeratorRepository;

import java.util.List;

public class ModeratorRepositoryImpl implements ModeratorRepository {

    @Override
    public List<Moderator> findAllModerators() {
        return Database.moderators;
    }

    @Override
    public void addModerator(Moderator moderator) {
        Database.moderators.add(moderator);
    }
}
