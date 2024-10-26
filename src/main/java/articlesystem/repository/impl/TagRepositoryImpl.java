package articlesystem.repository.impl;

import articlesystem.database.Database;
import articlesystem.model.Tag;
import articlesystem.repository.TagRepository;

import java.util.List;

public class TagRepositoryImpl implements TagRepository {
    @Override
    public List<Tag> findAllTags() {
        return Database.tags;
    }

    @Override
    public void addTag(Tag tag) {
        Database.tags.add(tag);
    }
}
