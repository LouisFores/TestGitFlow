package com.example.boxanime.service.login_logout;

import com.example.boxanime.model.User;

import java.sql.*;
import java.util.List;

public class JDBCLogin implements ILogin {
    private String url = "jdbc:mysql://localhost:3306/boxanime";
    private String userName = "root";
    private String password = "mySQL7122023@";
    private Connection connecting() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public void creatUser(User user) {
        String query = "INSERT INTO users(name, gender, gmail, userName, password) values (?,?,?,?,?);";
        Connection connection = connecting();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);

            statement.setString(1, user.getName());
            statement.setString(2, user.getGender());
            statement.setString(3, user.getGmail());
            statement.setString(4, user.getUserName());
            statement.setString(5, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkAccountUser(User user) {
        User user1 = null;
        String query = "SELECT gmail, password FROM users where gmail=?, password=?";
        PreparedStatement statement = null;
        try {
            statement = connecting().prepareStatement(query);
            statement.setString(1, user.getGmail());
            statement.setString(2, user.getPassword());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String gmail = resultSet.getString("gmail");
                String password = resultSet.getString("password");
                user1 = new User(gmail, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user1 == user) return true;
        else return false;
    }

    @Override
    public List<User> getListUser() {
        return null;
    }

    @Override
    public boolean updateUser(int id) {
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }
}
