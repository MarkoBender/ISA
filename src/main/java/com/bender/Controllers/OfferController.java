package com.bender.Controllers;

import com.bender.Beans.BuyingOrder;
import com.bender.Beans.Offer;
import com.bender.Beans.Restaurant;
import com.bender.Beans.Salesman;
import com.bender.Repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nikola on 15-02-17.
 */
@RestController
@RequestMapping(value="/offers")
public class OfferController {

    private OfferRepository repository ;

    @Autowired
    public OfferController(OfferRepository repository){
        this.repository = repository;

    }

    @RequestMapping(value = "/all")
    public List<Offer> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody Offer newOffer){
        repository.save(newOffer);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Offer updatedOffer) {
        Offer offer=updatedOffer;
        offer.setOffer_id(id);
        repository.save(offer);
    }

    @RequestMapping(value = "/salesmanoffers", method = RequestMethod.POST)
    public List<Offer> salesmanOffers(@RequestBody Salesman salesman) {
        return repository.findBySalesman(salesman);
    }

    @RequestMapping(value = "/offersfororder", method = RequestMethod.POST)
    public List<Offer> offersforOrder(@RequestBody BuyingOrder order) {
        return repository.findByMyorder(order);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id){
        repository.delete(id);
    }

}
