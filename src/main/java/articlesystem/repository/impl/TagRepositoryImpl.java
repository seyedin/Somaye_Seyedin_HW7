package articlesystem.repository.impl;

import articlesystem.database.DatabaseConnection;
import articlesystem.model.Tag;
import articlesystem.repository.TagRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagRepositoryImpl implements TagRepository {

    @Override
    public List<Tag> findAllTags() {
        String sql = "SELECT * FROM tags";
        List<Tag> tagList = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");

                Tag tag = new Tag(id, title);
                tagList.add(tag);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagList;

    }

    @Override
    public Tag addTag(Tag tag) {
        String sql = """
                 insert into tags(title)
                 VALUES (?)
                """;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, tag.getTitle());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                tag.setId(generatedId);
                return tag;
            } else {
                throw new SQLException("Inserting Tag failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
