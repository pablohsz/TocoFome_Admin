package com.pucgo.cliserver.tocofome_admin.dao;

import com.pucgo.cliserver.tocofome_admin.config.ConnectionFactory;
import com.pucgo.cliserver.tocofome_admin.model.Item;

import java.io.IOException;
import java.sql.*;


public class ItemDAO implements IItemDAO{
    @Override
    public Item crateItem(Item item) {
        try (Connection connection = ConnectionFactory.getConnection()){
            String query = "INSERT INTO items" +
                    "(product, description, image_url, price)" +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getProduct());
            statement.setString(2, item.getDescription());
            statement.setString(3, item.getImageUrl());
            statement.setBigDecimal(4, item.getPrice());
            statement.executeUpdate();
            //recuperando código do item que foi inserido no banco de dados
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next())
                item = new Item (resultSet.getLong("id"),
                        resultSet.getString("product"),
                        resultSet.getString("description"),
                        resultSet.getString("image_url"),
                        resultSet.getBigDecimal("price"));
        } catch (SQLException | IOException e) {
            System.err.print(e.getMessage());
            return null;
        }
        return item;
    }

    @Override
    public Item findById(Long id) {
        String query = "SELECT * FROM items WHERE id = ?";
        Item item = new Item();
        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                item = new Item(resultSet.getLong("id"),
                        resultSet.getString("product"),
                        resultSet.getString("description"),
                        resultSet.getString("image_url"),
                        resultSet.getBigDecimal("price")
                        );
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public Item findAll() {
        return null;
    }

    @Override
    public boolean deleteItem(Long id) {
        return false;
    }
}
