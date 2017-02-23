package com.bender.Controllers;

import com.bender.Beans.Guest;
import com.bender.Beans.Invitation;
import com.bender.Beans.Reservation;
import com.bender.Beans.Restaurant;
import com.bender.Repositories.GuestRepository;
import com.bender.Repositories.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bender on 1/5/2017.
 */
@RestController
@RequestMapping(value = "/invitations")
public class InvitationController {

    private InvitationRepository repository;
    private GuestRepository guestRepository;

    @Autowired
    public InvitationController(GuestRepository guestRepository,InvitationRepository repository) {
        this.guestRepository = guestRepository;
        this.repository = repository;
    }

    @RequestMapping(value = "/all")
    public List<Invitation> getAll(){
        return repository.findAll();
    }


    @RequestMapping(value = "/activeConfirmed/{id}")
    public List<Invitation> getActiveConfimedReservations(@PathVariable long id){
        Guest guest = guestRepository.findOne(id);
        List<Invitation> invitations = repository.findByInvited(guest);
        ArrayList<Invitation> activeConfirmed = new ArrayList<>();
        for(Invitation inv : invitations)
            if(inv.getReservation().getDateTime().after(new Date()))
                if(inv.isConfirmed())
                    activeConfirmed.add(inv);
        return activeConfirmed;
    }

    @RequestMapping(value = "/activeNotConfirmed/{id}")
    public List<Invitation> getActiveNotConfimedReservations(@PathVariable long id){
        Guest guest = guestRepository.findOne(id);
        List<Invitation> invitations = repository.findByInvited(guest);
        ArrayList<Invitation> activeConfirmed = new ArrayList<>();
        for(Invitation inv : invitations)
            if(inv.getReservation().getDateTime().after(new Date()))
                if(!inv.isConfirmed())
                    activeConfirmed.add(inv);
        return activeConfirmed;
    }

    @RequestMapping(value = "/confirmInvitation", method = RequestMethod.PUT)
    public void confirmReservation(@RequestBody Invitation invitation){
        invitation.setConfirmed(true);
        repository.save(invitation);
    }


    @RequestMapping(value = "/pastVisits/{id}")
    public List<Reservation> getInactiveReservations(@PathVariable long id){
        Guest guest = guestRepository.findOne(id);
        List<Invitation> invitations = repository.findByInvited(guest);
        ArrayList<Reservation> reservations = new ArrayList<>();
        for(Invitation inv : invitations)
            if(inv.getReservation().getDateTime().before(new Date()))
                if(inv.isConfirmed())
                    reservations.add(inv.getReservation());
        return reservations;
    }

    @RequestMapping(value = "/invitableFriends", method = RequestMethod.PUT)
    public List<Guest> getInvitableFriends(@RequestBody Reservation reservation){
        ArrayList<Guest> invitable = new ArrayList<>();
        Guest host = guestRepository.findOne(reservation.getHost().getUser_id());

        for(Guest friend : host.getFriends()){
            if(repository.findByInvitedAndReservation(friend,reservation) == null)
                invitable.add(friend);
        }
        return invitable;
    }

    @RequestMapping(value = "/getInvited", method = RequestMethod.PUT)
    public List<Invitation> getInvited(@RequestBody Reservation reservation){
        return repository.findByReservation(reservation);
    }


    @RequestMapping(value = "/add" , method = RequestMethod.PUT)
    public void addReservation(@RequestBody Invitation invitation){
        repository.save(invitation);
    }

    @RequestMapping(value = "/add/{id}" , method = RequestMethod.PUT)
    public void addById(@RequestBody Reservation reservation, @PathVariable long id){
        Guest invitedGuest = guestRepository.findOne(id);
        Invitation invitation = new Invitation();
        invitation.setReservation(reservation);
        invitation.setInvited(invitedGuest);
        repository.save(invitation);
    }

}
