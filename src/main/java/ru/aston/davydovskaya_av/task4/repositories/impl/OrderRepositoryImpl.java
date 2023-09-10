package ru.aston.davydovskaya_av.task4.repositories.impl;

import lombok.Cleanup;
import lombok.SneakyThrows;
import ru.aston.davydovskaya_av.task4.configs.MySqlConfig;
import ru.aston.davydovskaya_av.task4.entities.Order;
import ru.aston.davydovskaya_av.task4.repositories.DataEntityLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements DataEntityLayer<Order> {

    private static final String SELECT_ORDERS = "SELECT * FROM orders;";
    private static final String SELECT_ORDER = "SELECT * FROM orders WHERE id=?;";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE id=?;";
    private static final String CREATE_ORDER = "INSERT INTO orders (amount, unit_price) VALUES (?, ?);";
    private static final String UPDATE_ORDER = "UPDATE orders SET amount = ?, unit_price = ? WHERE id = ?;";

    @SneakyThrows(SQLException.class)
    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();

        @Cleanup Connection connection = MySqlConfig.createConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT_ORDERS);
        while (resultSet.next()) {
            orders.add(Order.builder()
                    .id(resultSet.getLong(1))
                    .amount(resultSet.getInt(2))
                    .unitPrice(resultSet.getBigDecimal(3))
                    .build());
        }

        return orders;
    }

    @SneakyThrows(SQLException.class)
    @Override
    public Order findById(Long id) {
        Order order = null;

        @Cleanup Connection connection = MySqlConfig.createConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ORDER);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            order = Order.builder()
                    .id(resultSet.getLong(1))
                    .amount(resultSet.getInt(2))
                    .unitPrice(resultSet.getBigDecimal(3))
                    .build();
        }

        return order;
    }

    @SneakyThrows(SQLException.class)
    @Override
    public boolean deleteById(Long id) {
        boolean isDelete = false;

        @Cleanup Connection connection = MySqlConfig.createConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_ORDER);
        statement.setLong(1, id);
        if (statement.executeUpdate() > 0) {
            isDelete = true;
        }

        return isDelete;
    }

    @SneakyThrows(SQLException.class)
    @Override
    public boolean create(Order order) {
        boolean isCreate = false;

        @Cleanup Connection connection = MySqlConfig.createConnection();
        @Cleanup PreparedStatement statement = connection.prepareStatement(CREATE_ORDER);
        statement.setInt(1, order.getAmount());
        statement.setBigDecimal(2, order.getUnitPrice());
        if (statement.executeUpdate() > 0) {
            isCreate = true;
        }

        return isCreate;
    }

    @SneakyThrows(SQLException.class)
    @Override
    public Order update(Order order) {
        Order resultOrder = null;

        @Cleanup Connection connection = MySqlConfig.createConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER);
        statement.setInt(1, order.getAmount());
        statement.setBigDecimal(2, order.getUnitPrice());
        statement.setLong(3, order.getId());
        if (statement.executeUpdate() > 0) {
            resultOrder = findById(order.getId());
        }

        return resultOrder;
    }
}
