package com.elemica.assignment.service;

import com.elemica.assignment.dao.ItemDao;
import com.elemica.assignment.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService{

    private final Map<String, ItemDao> itemDaos;

    @Autowired
    public ItemServiceImpl(Map<String, ItemDao> itemDaos) {
        this.itemDaos = itemDaos;
    }

    @Override
    public void addItem(Item item) {
        final String type = item.getType().toLowerCase();
        ItemDao itemDao = itemDaos.get(type);
        itemDao.addItem(item);
    }

    @Override
    public List<Item> getAll() {
        return itemDaos.get("raw").getAll();
    }
}
