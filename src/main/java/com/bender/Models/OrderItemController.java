package com.bender.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 2/28/2017.
 */
@RestController
@RequestMapping(value ="/orderItems")
public class OrderItemController {

    private OrderItemRepository repository;

    @Autowired
    public OrderItemController (OrderItemRepository repository){
        this.repository=repository;
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<OrderItem> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody OrderItem newOI){
        repository.save(newOI);
    }

    @RequestMapping(value = "/findOne/{id}")
    public OrderItem getById(@PathVariable long id){
        return repository.findOne(id);
    }


}
