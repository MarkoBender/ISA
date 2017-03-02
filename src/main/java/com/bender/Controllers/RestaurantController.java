package com.bender.Controllers;

import com.bender.Beans.Guest;
import com.bender.Beans.Reservation;
import com.bender.Beans.Restaurant;
import com.bender.Beans.RestaurantTable;
import com.bender.Repositories.ReservationRepository;
import com.bender.Repositories.RestaurantRepository;
import com.bender.Repositories.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bender on 1/5/2017.
 */

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private RestaurantRepository repository;
    private ReservationRepository reservationRepository;
    private RestaurantTableRepository restaurantTableRepository;

    @Autowired
    public RestaurantController(RestaurantRepository repository, ReservationRepository reservationRepository, RestaurantTableRepository restaurantTableRepository) {
        this.repository = repository;
        this.reservationRepository=reservationRepository;
        this.restaurantTableRepository=restaurantTableRepository;
    }

    @RequestMapping(value = "/all")
    public List<Restaurant> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody Restaurant newRestaurant){
        repository.save(newRestaurant);
    }

    @RequestMapping(value = "/findOne/{id}")
    public Restaurant getRestaurant(@PathVariable long id) {
        Restaurant restaurant = repository.findOne(id);
        return restaurant;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant restaurant=updatedRestaurant;
        restaurant.setRestaurant_id(id);
        repository.save(restaurant);
    }
    /*
   @RequestMapping(value="/dobaviRezervisaneStoloveZaVreme/{resID}/{vreme}",method=RequestMethod.POST)
    public List<RestaurantTable> dobaviRSZV(@PathVariable long resID, @PathVariable long vreme){
        System.out.println(vreme);
        Date date=new Date(vreme);
        Restaurant restaurant=repository.getOne(resID);
        System.out.println(restaurant.getName()+" je ima restorana za datum "+date);


        List<RestaurantTable> tables=new ArrayList<>();
        List<Reservation> rr=reservationRepository.findAll();
        for(Reservation r : rr){
            System.out.println("USAO SAM U JEBENU PETLJU");
            if(r.getRestaurant().getRestaurant_id()==restaurant.getRestaurant_id()){
                System.out.println("NASO SAM RESTORAN");
                Date datumPR=r.getDateTime();
                Date datumKR=new Date(datumPR.getTime());
                datumKR.setHours(datumKR.getHours()+r.getDuration());

                if(date.getTime()>=datumPR.getTime() && date.getTime()<=datumKR.getTime()){
                    System.out.println("datum pocetka "+datumPR);
                    System.out.println("datum kraja "+datumKR);
                    System.out.println("moj datum "+date);
                    System.out.println("DATUM DOBAR");
                    tables.addAll(r.getTables());
                }
            }
        }
        return tables;
    }*/

    @RequestMapping(value="/mozeLiSe/{resID}/{vreme}/{stoID}",method=RequestMethod.POST)
    public boolean mozeLiSe(@PathVariable long resID, @PathVariable long vreme, @PathVariable long stoID){
        System.out.println(vreme);
        Date date=new Date(vreme);
        Restaurant restaurant=repository.getOne(resID);

        List<RestaurantTable> tables=new ArrayList<>();
        List<Reservation> rr=reservationRepository.findAll();
        //RestaurantTable kkk=null;
        for(Reservation r : rr){
            if(r.getRestaurant().getRestaurant_id()==restaurant.getRestaurant_id()){
                Date datumPR=r.getDateTime();
                Date datumKR=new Date(datumPR.getTime());
                datumKR.setHours(datumKR.getHours()+r.getDuration());

                //if(date.after(datumPR) && date.before(datumKR)){
                if(date.getTime()>=datumPR.getTime() && date.getTime()<=datumKR.getTime()){
                    tables.addAll(r.getTables());
                    for(RestaurantTable t : r.getTables()) {
                        if(t.getRestaurant_table_id()==stoID){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


}
