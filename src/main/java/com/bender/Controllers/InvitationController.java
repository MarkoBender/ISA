package com.bender.Controllers;

import com.bender.Beans.Guest;
import com.bender.Beans.Invitation;
import com.bender.Beans.Reservation;
import com.bender.Beans.Restaurant;
import com.bender.Repositories.GuestRepository;
import com.bender.Repositories.InvitationRepository;
import com.bender.Repositories.ReservationRepository;
import com.bender.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
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
    private ReservationRepository reservationRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public InvitationController(GuestRepository guestRepository,InvitationRepository repository, ReservationRepository reservationRepository,RestaurantRepository restaurantRepository) {
        this.guestRepository = guestRepository;
        this.repository = repository;
        this.reservationRepository = reservationRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @RequestMapping(value = "/all")
    public List<Invitation> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value="/visitsForDay/{resid}/{i}",method = RequestMethod.POST)
    public int getVisitsForDay (@PathVariable long resid,@PathVariable int i,@RequestBody Date gotdaytoCheck){
        Calendar c = Calendar.getInstance();
        c.setTime(gotdaytoCheck);
        c.add(Calendar.DATE, i);
        Date daytoCheck = c.getTime();
        int numberOfVisits = 0;
        System.out.println(daytoCheck);
        Restaurant restaurant = restaurantRepository.findOne(resid);
        System.out.println(restaurant.getName());
        List<Reservation> reservations = new ArrayList<Reservation>();
        Date endDay = daytoCheck;
        c.setTime(endDay);
        c.add(Calendar.DATE, 1);
        endDay = c.getTime();
        for(Reservation res : reservationRepository.findByRestaurant(restaurant)){
            if(res.getDateTime().getTime() >= daytoCheck.getTime() && res.getDateTime().getTime() <= endDay.getTime()){
                reservations.add(res);
                numberOfVisits++;
            }
        }
        for(Reservation res : reservations){
            for(Invitation inv : repository.findByReservation(res)){
                if(inv.isConfirmed())
                    numberOfVisits++;
            }

        }
        return numberOfVisits;
    }

    @RequestMapping(value = "/activeConfirmed/{id}")
    public List<Invitation> getActiveConfimedReservations(@PathVariable long id){
        Guest guest = guestRepository.findOne(id);
        List<Invitation> invitations = repository.findByInvited(guest);
        ArrayList<Invitation> activeConfirmed = new ArrayList<>();
        for(Invitation inv : invitations){
            Date datum = inv.getReservation().getDateTime();
            Date resDate=new Date(datum.getTime());
            resDate.setHours(resDate.getHours() + inv.getReservation().getDuration());
            /*Date resDate = new Date();
            resDate.setHours(resDate.getHours()+inv.getReservation().getDuration());*/
            if (resDate.after(new Date()))
                if (inv.isConfirmed())
                    activeConfirmed.add(inv);
        }
        return activeConfirmed;
    }

    @RequestMapping(value = "/activeNotConfirmed/{id}")
    public List<Invitation> getActiveNotConfimedReservations(@PathVariable long id){
        Guest guest = guestRepository.findOne(id);
        List<Invitation> invitations = repository.findByInvited(guest);
        ArrayList<Invitation> activeConfirmed = new ArrayList<>();
        for(Invitation inv : invitations) {
            Date datum = inv.getReservation().getDateTime();
            Date resDate=new Date(datum.getTime());
            resDate.setHours(resDate.getHours() + inv.getReservation().getDuration());
            /*Date resDate = new Date();
            resDate.setHours(resDate.getHours()+inv.getReservation().getDuration());*/
            if (resDate.after(new Date()))
                if (!inv.isConfirmed())
                    activeConfirmed.add(inv);
        }
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
        for(Invitation inv : invitations) {
            Date datum = inv.getReservation().getDateTime();
            Date resDate=new Date(datum.getTime());
            resDate.setHours(resDate.getHours() + inv.getReservation().getDuration());
            /*Date resDate = new Date();
            resDate.setHours(resDate.getHours()+inv.getReservation().getDuration());*/
            if (resDate.before(new Date()))
                if (inv.isConfirmed())
                    reservations.add(inv.getReservation());
        }
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
