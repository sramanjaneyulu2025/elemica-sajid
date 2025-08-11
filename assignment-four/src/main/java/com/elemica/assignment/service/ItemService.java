package com.elemica.assignment.service;

import com.elemica.assignment.model.Item;

import java.util.List;

public interface ItemService {
    public void addItem(Item item);
    public List<Item> getAll();
}
