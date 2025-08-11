package com.elemica.assignment.dao;

import com.elemica.assignment.model.Item;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("raw")
public class RawItemDao implements ItemDao{

    @Getter
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RawItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
