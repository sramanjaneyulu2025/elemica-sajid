package com.elemica.assignment.controller;

import com.elemica.assignment.model.Item;
import com.elemica.assignment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assignment-four")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public void addItem(@RequestBody Item item){
        itemService.addItem(item);
    }

    @GetMapping
    public List<Item> getAll(){
        return itemService.getAll();
    }
}
