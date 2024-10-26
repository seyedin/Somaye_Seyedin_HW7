package articlesystem.model;

import articlesystem.model.enums.ArticleStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class Article {
    private int id;
    private String title;
    private String brief;
    private String content;
    private Date createDate;
    private boolean isPublished;
    private Date lastUpdateDate;
    private Date publishDate;
    private ArticleStatus status;
    private Category category;
    private List<Tag> tags;

    public Article(int id, String title, String brief, String content, Category category) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = new Date();
        this.isPublished = false;
        this.status = ArticleStatus.DRAFT;
        this.category = category;
        this.tags = new ArrayList<>();
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

}




