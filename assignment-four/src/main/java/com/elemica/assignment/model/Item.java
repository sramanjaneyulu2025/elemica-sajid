package com.elemica.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String name, type;
    private int price, quantity;

    @JsonProperty
    private double salesTax, finalPrice;

    public Item(@JsonProperty("name") String name,
                @JsonProperty("type") String type,
                @JsonProperty("price") int price,
                @JsonProperty("quantity") int quantity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }
}
