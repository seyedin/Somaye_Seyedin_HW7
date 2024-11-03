package articlesystem.repository.impl;

import articlesystem.database.DatabaseConnection;
import articlesystem.model.Author;
import articlesystem.model.User;
import articlesystem.repository.AuthorRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    @Override
    public List<Author> findAllAuthors() {
        String sql = """
                select a.id id, nationalcode, birthday, username, password
                from authors a,
                     users u
                where u.id = a.id
                """;
        List<Author> authors = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nationalCode = resultSet.getString("nationalcode");
                Date birthday = resultSet.getDate("birthday");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                LocalDate birthDate = birthday.toLocalDate();

                Author author = new Author(id, username, nationalCode, birthDate, password);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public void addUserAndAuthor(Author author) {
        String insertUserQuery = """
                insert into users (username, password)
                values (?, ?);
                """;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatementUsers = conn.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatementUsers.setString(1, author.getUsername());
            preparedStatementUsers.setString(2, author.getPassword());

            preparedStatementUsers.executeUpdate();

            User user = getUserByUsernameAndPassword(author.getUsername(), author.getPassword());
            int id = user.getId();
            addAuthor(id, author);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) {
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

    private void addAuthor(int userId, Author author) {
        String sql = """
                insert into authors (id, nationalCode, birthday)
                       values (?, ?, ?);
                """;

        Date birthDate = Date.valueOf(author.getBirthday()); // convert LocalDate to sql Data

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, author.getNationalCode());
            preparedStatement.setDate(3, birthDate);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
