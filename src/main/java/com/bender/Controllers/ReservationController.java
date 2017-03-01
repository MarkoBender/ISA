package com.bender.Controllers;

import com.bender.Beans.Guest;
import com.bender.Beans.Invitation;
import com.bender.Beans.Reservation;
import com.bender.Beans.RestaurantTable;
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
        List<Reservation> reservations = repository.findByHost(host);
        ArrayList<Reservation> active = new ArrayList<>();
        for(Reservation res : reservations){
            //Date resDate = res.getDateTime();
            Date datum = res.getDateTime();
            Date resDate=new Date(datum.getTime());
            resDate.setHours(resDate.getHours() + res.getDuration());
            if(resDate.after(new Date()))
                active.add(res);

        }
        return active;
    }

    @RequestMapping(value = "/allInactive/{id}")
    List<Reservation> getAllInactive(@PathVariable long id){
        Guest host = guestRepository.findOne(id);
        List<Reservation> reservations = repository.findByHost(host);
        ArrayList<Reservation> inactive = new ArrayList<>();
        for(Reservation res : reservations){
            //Date resDate = res.getDateTime();
            Date datum = res.getDateTime();
            Date resDate=new Date(datum.getTime());
            resDate.setHours(resDate.getHours() + res.getDuration());
            if(resDate.before(new Date()))
                inactive.add(res);

        }
        return inactive;
    }

    @RequestMapping(value = "/add",method = RequestMethod.PUT)
    Reservation addReservation(@RequestBody Reservation reservation){
        repository.save(reservation);
        return reservation;
    }

    @RequestMapping(value="/setInactive", method=RequestMethod.PUT)
    public Reservation setIA(@RequestBody Reservation reservation){
        //reservation.setStatus("closed");
        reservation.setStatus("zatrazen");
        //reservation.setTables(new ArrayList<RestaurantTable>());
        repository.save(reservation);

        return reservation;
    }

}
