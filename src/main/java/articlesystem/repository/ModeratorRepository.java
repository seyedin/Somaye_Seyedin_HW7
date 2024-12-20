package articlesystem.repository;

import articlesystem.model.Moderator;

import java.util.List;

public interface ModeratorRepository {

    List<Moderator> findAllModerators();

    void addUserAndModerator(Moderator moderator);
}
