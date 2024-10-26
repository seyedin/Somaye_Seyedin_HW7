package articlesystem.service;

import articlesystem.model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findAllTags();

    void addTag(Tag category);

    Tag findOrCreateTag(String tagTitle);
}
