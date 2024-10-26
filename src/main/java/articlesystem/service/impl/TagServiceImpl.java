package articlesystem.service.impl;

import articlesystem.model.Tag;
import articlesystem.repository.TagRepository;
import articlesystem.repository.impl.TagRepositoryImpl;
import articlesystem.service.TagService;

import java.util.List;

public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository = new TagRepositoryImpl();
    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAllTags();
    }

    @Override
    public void addTag(Tag category) {
        tagRepository.addTag(category);
    }

    @Override
    public Tag findOrCreateTag(String tagTitle) {
        List<Tag> allTags = tagRepository.findAllTags();
        for (Tag tag : allTags) {
            if (tag.getTitle().equalsIgnoreCase(tagTitle)) {
                return tag;
            }
        }
        Tag newTag = new Tag(allTags.size() + 1, tagTitle);
        addTag(newTag);
        return newTag;
    }
}
