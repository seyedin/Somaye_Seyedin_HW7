package articlesystem.repository.impl;

import articlesystem.database.DatabaseConnection;
import articlesystem.model.Category;
import articlesystem.repository.CategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public List<Category> findAllCategory() {
        String sql = "SELECT * FROM categories";
        List<Category> categoryList = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Category category = new Category(id, title, description);
                categoryList.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }


    @Override
    public Category addCategory(Category category) {
        String sql = """
                insert into categories(title, description)
                VALUES (?,?)
                """;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set the parameters
            preparedStatement.setString(1, category.getTitle());
            preparedStatement.setString(2, category.getDescription());

            // Execute the update and retrieve generated keys
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                // Set the ID of the category object
                category.setId(generatedId);

                System.out.println("Category inserted successfully with ID: " + generatedId);
                return category;
            } else {
                throw new SQLException("Inserting category failed, no ID obtained.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
