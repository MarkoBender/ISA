package com.bender.Controllers;

import com.bender.Beans.BuyingOrder;

import com.bender.Beans.Offer;
import com.bender.Beans.Restaurant;
import com.bender.Repositories.BuyingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nikola on 14-02-17.
 */
@RestController
@RequestMapping(value="/buyingorders")
public class BuyingOrderController {

    private BuyingOrderRepository repository;

    @Autowired
    public BuyingOrderController(BuyingOrderRepository repository) {
        this.repository=repository;
    }

    @RequestMapping(value = "/all")
    public List<BuyingOrder> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody BuyingOrder order) {
        BuyingOrder updatedorder=order;
        updatedorder.setBuying_order_id(id);
        repository.save(updatedorder);
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody BuyingOrder newBuyingOrder){
        newBuyingOrder.setStatus("Active");
        repository.save(newBuyingOrder);
    }
    @RequestMapping(value = "/findOne/{id}")
    public BuyingOrder getOrder(@PathVariable long id) {
        BuyingOrder order = repository.findOne(id);
        return order;
    }
    @RequestMapping(value = "/forRestaurant", method=RequestMethod.POST)
    public List<BuyingOrder> getforRestaurant(@RequestBody Restaurant restaurant){
        return repository.findByRestaurant(restaurant);
    }
}
