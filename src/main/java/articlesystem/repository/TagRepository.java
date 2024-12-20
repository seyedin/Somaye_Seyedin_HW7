package articlesystem.repository;

import articlesystem.model.Tag;

import java.util.List;

public interface TagRepository {

    List<Tag> findAllTags();

    Tag addTag(Tag category);
}
