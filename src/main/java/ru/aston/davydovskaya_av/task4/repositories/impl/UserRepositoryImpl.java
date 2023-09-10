package ru.aston.davydovskaya_av.task4.repositories.impl;

import lombok.Cleanup;
import lombok.SneakyThrows;
import ru.aston.davydovskaya_av.task4.configs.MySqlConfig;
import ru.aston.davydovskaya_av.task4.entities.User;
import ru.aston.davydovskaya_av.task4.repositories.DataEntityLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements DataEntityLayer<User> {

    private static final String SELECT_USERS = "SELECT * FROM users;";
    private static final String SELECT_USER = "SELECT * FROM users WHERE id=?;";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?;";
    private static final String CREATE_USER = "INSERT INTO users " +
            "(first_name, last_name, patronymic, phone_number, email, order_id) " +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_USER = "UPDATE users " +
            "SET first_name = ?, last_name = ?, patronymic = ?, phone_number = ?, email = ?, order_id = ? " +
            "WHERE id = ?;";
    private static final String SELECT_USERS_WITH_ORDERS
            = "SELECT last_name, amount, unit_price FROM users JOIN orders on orders.id = users.order_id;";

    @SneakyThrows(SQLException.class)
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        @Cleanup Connection connection = MySqlConfig.createConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT_USERS);
        while (resultSet.next()) {
            users.add(User.builder()
                    .id(resultSet.getLong(1))
                    .firstName(resultSet.getString(2))
                    .lastName(resultSet.getString(3))
                    .patronymic(resultSet.getString(4))
                    .phoneNumber(resultSet.getString(5))
                    .email(resultSet.getString(6))
                    .orderId(resultSet.getLong(7))
                    .build());
        }

        return users;
    }

    @SneakyThrows(SQLException.class)
    @Override
    public User findById(Long id) {
        User user = null;

        @Cleanup Connection connection = MySqlConfig.createConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_USER);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = User.builder()
                    .id(resultSet.getLong(1))
                    .firstName(resultSet.getString(2))
                    .lastName(resultSet.getString(3))
                    .patronymic(resultSet.getString(4))
                    .phoneNumber(resultSet.getString(5))
                    .email(resultSet.getString(6))
                    .orderId(resultSet.getLong(7))
                    .build();
        }

        return user;
    }

    @SneakyThrows(SQLException.class)
    @Override
    public boolean deleteById(Long id) {
        boolean isDelete = false;

        @Cleanup Connection connection = MySqlConfig.createConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_USER);
        statement.setLong(1, id);
        if (statement.executeUpdate() > 0) {
            isDelete = true;
        }

        return isDelete;
    }

    @SneakyThrows(SQLException.class)
    @Override
    public boolean create(User user) {
        boolean isCreate = false;

        @Cleanup Connection connection = MySqlConfig.createConnection();
        PreparedStatement statement = connection.prepareStatement(CREATE_USER);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getPatronymic());
        statement.setString(4, user.getPhoneNumber());
        statement.setString(5, user.getEmail());
        statement.setLong(6, user.getOrderId());
        if (statement.executeUpdate() > 0) {
            isCreate = true;
        }

        return isCreate;
    }

    @SneakyThrows(SQLException.class)
    @Override
    public User update(User user) {
        User resultUser = null;

        @Cleanup Connection connection = MySqlConfig.createConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getPatronymic());
        statement.setString(4, user.getPhoneNumber());
        statement.setString(5, user.getEmail());
        statement.setLong(6, user.getOrderId());
        statement.setLong(7, user.getId());
        if (statement.executeUpdate() > 0) {
            resultUser = findById(user.getId());
        }

        return resultUser;
    }

    @SneakyThrows(SQLException.class)
    public List<String> getUsersWithOrders() {
        List<String> listJoin = new ArrayList<>();

        @Cleanup Connection connection = MySqlConfig.createConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT_USERS_WITH_ORDERS);
        while (resultSet.next()) {
            listJoin.add("Пользователь: " + resultSet.getString(1)
                    + " Количество товаров: " + resultSet.getInt(2)
                    + " Начальная цена: " + resultSet.getBigDecimal(3));
        }

        return listJoin;
    }
}
