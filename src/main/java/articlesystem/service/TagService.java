package articlesystem.service;

import articlesystem.model.Tag;

public interface TagService {

    void addTag(Tag category);
    Tag findOrCreateTag(String tagTitle);
}
