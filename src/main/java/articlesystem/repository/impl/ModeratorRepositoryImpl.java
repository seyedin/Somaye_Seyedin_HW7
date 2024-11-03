package articlesystem.repository.impl;

import articlesystem.database.DatabaseConnection;
import articlesystem.model.Moderator;
import articlesystem.model.User;
import articlesystem.repository.ModeratorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeratorRepositoryImpl implements ModeratorRepository {

    @Override
    public List<Moderator> findAllModerators() {
        String sql = """
                select a.id id, username, password
                from moderators a,
                     users u
                where u.id = a.id
                """;
        List<Moderator> moderators = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                Moderator moderator = new Moderator(id, username, password);
                moderators.add(moderator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moderators;
    }

    @Override
    public void addUserAndModerator(Moderator moderator) {
        String insertUserQuery = """
                insert into users (username, password)
                values (?, ?);
                """;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatementUsers = conn.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatementUsers.setString(1, moderator.getUsername());
            preparedStatementUsers.setString(2, moderator.getPassword());

            preparedStatementUsers.executeUpdate();

            User user = getUserByUsernameAndPassword(moderator.getUsername(), moderator.getPassword());
            int id = user.getId();
            addModerator(id);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserByUsernameAndPassword(String username, String password) {
        String sql = """
                select * from users where username = ? and password = ?
                """;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String usernameUser = resultSet.getString("username");
                String passwordUser = resultSet.getString("password");

                User user = new User(id, usernameUser, passwordUser);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addModerator(int userId) {
        String sql = """
                insert into moderators (id)
                       values (?);
                """;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
