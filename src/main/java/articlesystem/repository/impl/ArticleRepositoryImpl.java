package articlesystem.repository.impl;

import articlesystem.database.DatabaseConnection;
import articlesystem.model.Article;
import articlesystem.model.enums.ArticleStatus;
import articlesystem.repository.ArticleRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {

    @Override
    public List<Article> findAllArticlesByAuthorId(int authorId) {
        String sql = "select * from articles WHERE  author_id = ?";
        List<Article> articles = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String brief = resultSet.getString("brief");
                String content = resultSet.getString("content");
                LocalDate createDate = resultSet.getDate("create_date").toLocalDate();
                LocalDate publishDate = resultSet.getDate("publish_date") != null ? resultSet.getDate("publish_date").toLocalDate() : null;
                LocalDate lastUpdateDate = resultSet.getDate("last_update_date") != null ? resultSet.getDate("last_update_date").toLocalDate() : null;
                boolean isPublished = resultSet.getBoolean("is_published");
                String articleStatus = resultSet.getString("article_status");
                Article article = new Article();
                article.setId(id);
                article.setTitle(title);
                article.setBrief(brief);
                article.setContent(content);
                article.setCreateDate(createDate);
                article.setPublishDate(publishDate);
                article.setLastUpdateDate(lastUpdateDate);
                article.setPublished(isPublished);
                ArticleStatus status = ArticleStatus.valueOf(articleStatus); // convert String to enum
                article.setStatus(status);

                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public void addArticle(Article article, int authorId, int categoryId) {
        String sql = """
                insert into articles(title, brief, content, create_date,
                                     publish_date, last_update_date,
                                     is_published, article_status,
                                     category_id, author_id)
                values (?, ?, ?,
                        ?,?,
                        ?,?, ?,
                        ?,?)
                """;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getBrief());
            preparedStatement.setString(3, article.getContent());
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            preparedStatement.setDate(5, null);
            preparedStatement.setDate(6, null);
            preparedStatement.setBoolean(7, false);
            preparedStatement.setString(8, article.getStatus().toString());
            preparedStatement.setInt(9, categoryId);
            preparedStatement.setInt(10, authorId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Article findArticleById(int id) {
        String sql = "SELECT * FROM ARTICLES WHERE ID = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int articleId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String brief = resultSet.getString("brief");
                String content = resultSet.getString("content");
                LocalDate createDate = resultSet.getDate("create_date").toLocalDate();
                LocalDate publishDate = resultSet.getDate("publish_date") != null ? resultSet.getDate("publish_date").toLocalDate() : null;
                LocalDate lastUpdateDate = resultSet.getDate("last_update_date") != null ? resultSet.getDate("last_update_date").toLocalDate() : null;
                boolean isPublished = resultSet.getBoolean("is_published");
                String articleStatus = resultSet.getString("article_status");
                Article article = new Article();
                article.setId(articleId);
                article.setTitle(title);
                article.setBrief(brief);
                article.setContent(content);
                article.setCreateDate(createDate);
                article.setPublishDate(publishDate);
                article.setLastUpdateDate(lastUpdateDate);
                article.setPublished(isPublished);
                ArticleStatus status = ArticleStatus.valueOf(articleStatus); // convert String to enum
                article.setStatus(status);

                return article;
            } else {
                System.out.println("Article not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Article> findArticleByCategoryId(int categoryId) {
        String sql = "SELECT * FROM articles a WHERE a.category_id = ?";
        List<Article> articles = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String brief = resultSet.getString("brief");
                String content = resultSet.getString("content");
                LocalDate createDate = resultSet.getDate("create_date").toLocalDate();
                LocalDate publishDate = resultSet.getDate("publish_date").toLocalDate();
                LocalDate lastUpdateDate = resultSet.getDate("last_update_date").toLocalDate();
                boolean isPublished = resultSet.getBoolean("is_published");
                String articleStatus = resultSet.getString("article_status");
                Article article = new Article();
                article.setId(id);
                article.setTitle(title);
                article.setBrief(brief);
                article.setContent(content);
                article.setCreateDate(createDate);
                article.setPublishDate(publishDate);
                article.setLastUpdateDate(lastUpdateDate);
                article.setPublished(isPublished);
                ArticleStatus status = ArticleStatus.valueOf(articleStatus); // convert String to enum
                article.setStatus(status);

                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public List<Article> findAllPendingArticles() {
        String sql = "SELECT * FROM articles a WHERE a.article_status = 'PENDING' ORDER BY create_date DESC";
        List<Article> articles = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String brief = resultSet.getString("brief");
                String content = resultSet.getString("content");
                LocalDate createDate = resultSet.getDate("create_date").toLocalDate();
                LocalDate publishDate = resultSet.getDate("publish_date") != null ? resultSet.getDate("publish_date").toLocalDate() : null;
                LocalDate lastUpdateDate = resultSet.getDate("last_update_date") != null ? resultSet.getDate("last_update_date").toLocalDate() : null;
                boolean isPublished = resultSet.getBoolean("is_published");
                String articleStatus = resultSet.getString("article_status");
                Article article = new Article();
                article.setId(id);
                article.setTitle(title);
                article.setBrief(brief);
                article.setContent(content);
                article.setCreateDate(createDate);
                article.setPublishDate(publishDate);
                article.setLastUpdateDate(lastUpdateDate);
                article.setPublished(isPublished);
                ArticleStatus status = ArticleStatus.valueOf(articleStatus); // convert String to enum
                article.setStatus(status);

                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public boolean updateArticle(int articleId, String newTitle, String newBrief, String newContent) {
        String sql = """
                update articles
                set title            = ?,
                    brief            = ?,
                    content          = ?,
                    last_update_date = current_date
                where id = ?;                
                """;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            // Set the parameters for the update
            preparedStatement.setString(1, newTitle);
            preparedStatement.setString(2, newBrief);
            preparedStatement.setString(3, newContent);
            preparedStatement.setInt(4, articleId);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                return true;
            } else {
                System.out.println("No Article found with the given ID.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean approveOrRejectArticle(int articleId, boolean isPublished, ArticleStatus status) {
        String sql = """
                update articles
                set article_status = ?,
                    is_published   = ?,
                    publish_date   = ?
                where id = ?;             
                """;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            // Set the parameters for the update
            preparedStatement.setString(1, status.name());
            preparedStatement.setBoolean(2, isPublished);
            preparedStatement.setDate(3, isPublished ? new java.sql.Date(System.currentTimeMillis()) : null);
            preparedStatement.setInt(4, articleId);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                return true;
            } else {
                System.out.println("No Article found with the given ID.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}