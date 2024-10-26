package articlesystem.model;

import articlesystem.model.enums.ArticleStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
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

    // Getters and Setters

    public String getContent() {
        return content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Category getCategory() {
        return category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPublished(boolean published) {
        this.isPublished = published;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getBrief() {
        return brief;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }


    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", isPublished=" + isPublished +
                ", lastUpdateDate=" + lastUpdateDate +
                ", publishDate=" + publishDate +
                '}';
    }
}




