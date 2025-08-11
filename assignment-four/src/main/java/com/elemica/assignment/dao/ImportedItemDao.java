package com.elemica.assignment.dao;

import com.elemica.assignment.model.Item;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("imported")
public class ImportedItemDao implements ItemDao{

    @Getter
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ImportedItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public double calculateFinalPrice(Item item) {
        double importDuty = item.getPrice() * 0.1 ;
        double dutyAndTaxAddedCost = importDuty + item.getSalesTax() + item.getPrice();
        double surcharge = 0;

        if(dutyAndTaxAddedCost > 200){ surcharge = 0.05*dutyAndTaxAddedCost;}
        else if(dutyAndTaxAddedCost > 100) {surcharge = 10;}
        else {surcharge = 5;}

        return item.getPrice() + item.getSalesTax() + importDuty + surcharge;
    }
}
