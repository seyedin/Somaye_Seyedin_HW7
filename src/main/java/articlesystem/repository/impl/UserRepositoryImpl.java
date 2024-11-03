package articlesystem.repository.impl;

import articlesystem.database.DatabaseConnection;
import articlesystem.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean updatePassword(int authorId, String newPassword) {
        String sql = """
                update users  set password =  ? where id = ?;
                """;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            // Set the parameters for the update
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, authorId);


            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println("Author Password updated successfully.");
                return true;
            } else {
                System.out.println("No Author found with the given ID.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
