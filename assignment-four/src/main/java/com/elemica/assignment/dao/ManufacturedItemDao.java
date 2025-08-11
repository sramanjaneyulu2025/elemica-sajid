package com.elemica.assignment.dao;

import com.elemica.assignment.model.Item;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("manufactured")
public class ManufacturedItemDao implements ItemDao{

    @Getter
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ManufacturedItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public double calculateSalesTax(Item item) {
        double salesTax = 0.125 * item.getPrice();
        return salesTax = salesTax + 0.02 * (item.getPrice() + salesTax);
    }
}
