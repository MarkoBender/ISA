package com.bender.Controllers;

import com.bender.Beans.*;
import com.bender.Repositories.FoodDrinkItemRepository;
import com.bender.Repositories.OcenaRepository;
import com.bender.Repositories.OrderItemRepository;
import com.bender.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 3/1/2017.
 */
@RestController
@RequestMapping(value ="/ocene")
public class OcenaController {

    private OcenaRepository repository;
    private ReservationRepository reservationRepository;
    private OrderItemRepository orderItemRepository;
    private FoodDrinkItemRepository foodDrinkItemRepository;

    @Autowired
    public OcenaController (OcenaRepository repository, ReservationRepository reservationRepository,
                            OrderItemRepository orderItemRepository, FoodDrinkItemRepository foodDrinkItemRepository){
        this.repository=repository;
        this.reservationRepository=reservationRepository;
        this.orderItemRepository = orderItemRepository;
        this.foodDrinkItemRepository = foodDrinkItemRepository;
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Ocena> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody Ocena newOcena){
        repository.save(newOcena);
    }

    @RequestMapping(value = "/findOne/{id}")
    public Ocena getById(@PathVariable long id){
        return repository.findOne(id);
    }

    @RequestMapping(value="/findByGuestAndReservation/{resID}", method = RequestMethod.POST)
    public Ocena findBGAR(@PathVariable long resID, @RequestBody Guest guest ){
        Reservation reservation=reservationRepository.findOne(resID);
        List<Ocena> ocene=repository.findAll();
        Ocena ocena=null;
        for(Ocena o: ocene){
            if(o.getReservation().getReservation_id()==reservation.getReservation_id()){
                if(o.getGuest().getUser_id()==guest.getUser_id()){
                    ocena=o;
                    return ocena;
                }
            }
        }

        ocena=new Ocena();
        ocena.setOcena_id(-1);
        return ocena;
    }

    @RequestMapping(value="/gradeForProduct", method=RequestMethod.POST)
    public double averageForProduct (@RequestBody FoodDrinkItem item){
        double average = 0;
        int counter = 0;
        for(OrderItem order : orderItemRepository.findByFoodDrinkItem(item)){
            Reservation reservation = order.getReservation();
            for(Ocena ocena : repository.findAll()){
                if(ocena.getReservation().getReservation_id() == reservation.getReservation_id()){
                    counter ++ ;
                    average += ocena.getOcenaUsluge();
                }

            }
        }
        if(counter == 0)
            return average;
        average = average / counter;
        return average;
    }

    @RequestMapping(value="/gradeForSteward", method=RequestMethod.POST)
    public double averageforSteward (@RequestBody Steward steward){
        double average =0 ;
        int counter =0;
        for(OrderItem order : orderItemRepository.findBySteward(steward)){
            Reservation reservation = order.getReservation();
            for(Ocena ocena : repository.findAll()){
                if(ocena.getReservation().getReservation_id() == reservation.getReservation_id()){
                    counter ++ ;
                    average += ocena.getOcenaUsluge();
                }
            }
        }
        if(counter == 0)
            return average;
        average = average / counter;
        return average;
    }

    @RequestMapping(value="/gradeforRestaurant", method=RequestMethod.POST)
    public double averageRestaurantGrad (@RequestBody Restaurant restaurant){
        double average =0;
        int counter =0;
        for(Reservation reservation : reservationRepository.findAll()){
            if(reservation.getRestaurant().getRestaurant_id() == restaurant.getRestaurant_id()){
                for(Ocena ocena : repository.findAll()){
                    if(ocena.getReservation().getReservation_id() == reservation.getReservation_id()){
                        average += ocena.getOcenaRestorana();
                        counter++;
                    }
                }
            }
        }
        if(counter == 0)
            return average;
        average = average / counter;
        return average;
    }
}
