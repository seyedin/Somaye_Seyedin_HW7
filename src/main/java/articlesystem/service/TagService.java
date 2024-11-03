package articlesystem.service;

import articlesystem.model.Tag;

public interface TagService {

    Tag addTag(Tag category);
    Tag findOrCreateTag(String tagTitle);
}
