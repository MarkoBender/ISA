package com.bender.Controllers;

import com.bender.Beans.Guest;
import com.bender.Beans.Reservation;
import com.bender.Repositories.GuestRepository;
import com.bender.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bender on 1/5/2017.
 */
@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {

    private GuestRepository guestRepository;
    private ReservationRepository repository;

    @Autowired
    public ReservationController(GuestRepository guestRepository, ReservationRepository repository) {
        this.guestRepository = guestRepository;
        this.repository = repository;
    }

    @RequestMapping(value = "/all")
    List<Reservation> getAll(){
        return repository.findAll();
    }



    @RequestMapping(value = "/allActive/{id}")
    List<Reservation> getAllActive(@PathVariable long id){
        Guest host = guestRepository.findOne(id);
        return repository.findByHostAndDateTimeAfter(host,new Date());
    }

    @RequestMapping(value = "/allInactive/{id}")
    List<Reservation> getAllInactive(@PathVariable long id){
        Guest host = guestRepository.findOne(id);
        return repository.findByHostAndDateTimeBefore(host,new Date());
    }

    @RequestMapping(value = "/add",method = RequestMethod.PUT)
    Reservation addReservation(@RequestBody Reservation reservation){
        repository.save(reservation);
        return reservation;
    }



}
