package com.bender.Controllers;

import com.bender.Beans.*;
import com.bender.Repositories.GuestRepository;
import com.bender.Repositories.InvitationRepository;
import com.bender.Repositories.OrderItemRepository;
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
    private OrderItemRepository orderItemRepository;
    private InvitationRepository invitationRepository;

    @Autowired
    public ReservationController(GuestRepository guestRepository, ReservationRepository repository, OrderItemRepository orderItemRepository, InvitationRepository invitationRepository) {
        this.guestRepository = guestRepository;
        this.repository = repository;
        this.orderItemRepository=orderItemRepository;
        this.invitationRepository=invitationRepository;
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

    @RequestMapping(value="/isActive/{id}")
    Boolean isReserved (@PathVariable long id){
        Boolean isActive = false;
        List<Reservation> reservations = repository.findAll();
        for(Reservation res : reservations){
            Date resDate = res.getDateTime();
            resDate.setHours(resDate.getHours() + res.getDuration());
            if(resDate.after(new Date())){
                for(RestaurantTable t : res.getTables()){
                    if(t.getRestaurant_table_id() == id)
                        isActive = true;
                }
            }
        }
        return isActive;
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

    @RequestMapping(value="/izbrisi/{resID}", method=RequestMethod.DELETE)
    public void del(@PathVariable long resID){

        List<Invitation> invitations=new ArrayList<>(invitationRepository.findAll());

        for(Invitation i : invitations){
            if(i.getReservation().getReservation_id()==resID) {
                invitationRepository.delete(invitationRepository.findOne(i.getInvitation_id()));
                System.out.println("BRISEM POZIVNICU");
            }
        }

        List<OrderItem> orderItems=new ArrayList<>(orderItemRepository.findAll());
        for(OrderItem oi: orderItems){
            if(oi.getReservation().getReservation_id()==resID) {
                orderItemRepository.delete(orderItemRepository.findOne(oi.getOrderItem_id()));
                System.out.println("BRISEM JELO");
            }
        }

        repository.delete(repository.findOne(resID));
    }

}
