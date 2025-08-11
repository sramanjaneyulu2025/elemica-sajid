package com.elemica.assignment.dao;

import com.elemica.assignment.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.UUID;

public interface ItemDao {
    public JdbcTemplate getJdbcTemplate();


    public default double calculateSalesTax(Item item) {
        return 0.125 * item.getPrice();
    }

    public default double calculateFinalPrice(Item item) {
        return item.getPrice() + item.getSalesTax();
    }

    public default List<Item> getAll(){
        final String sqlQuery = "SELECT * FROM items";
        return getJdbcTemplate().query(sqlQuery, (resultSet, i) -> {
            Item item = new Item(resultSet.getString("name"), resultSet.getString("type"),
                    Integer.parseInt(resultSet.getString("price")),
                    Integer.parseInt(resultSet.getString("quantity")));
            item.setSalesTax(Double.parseDouble(resultSet.getString("salesTax")));
            item.setFinalPrice(Double.parseDouble(resultSet.getString("finalPrice")));
            return item;
        });
    }

    public default void addItem(Item item){
        final double salesTax = this.calculateSalesTax(item);
        item.setSalesTax(salesTax);

        final double finalPrice = this.calculateFinalPrice(item);
        item.setFinalPrice(finalPrice);

        final String sqlQuery = "INSERT INTO items (name, type, price, quantity, salesTax, finalPrice) VALUES (?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sqlQuery, item.getName(), item.getType(), item.getPrice(), item.getQuantity(), item.getSalesTax(), item.getFinalPrice());
    };
}
