package com.bender.Controllers;

import com.bender.Beans.Guest;
import com.bender.Beans.User;
import com.bender.Repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bender on 12/25/2016.
 */
@RestController
@RequestMapping(value = "/guests")
public class GuestController {

    private GuestRepository repository;

    @Autowired
    JavaMailSenderImpl sender;

    @Autowired
    public GuestController(GuestRepository repository) {
        this.repository = repository;

    }


    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Guest> getAll(){return repository.findAll();}

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody Guest newGuest){
        //newGuest.setUloga("Guest");
        //repository.save(newGuest);


        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("marko.bender@live.com");
        msg.setFrom("isa2017isa2017isa@gmail.com");
        msg.setText("jedi gowna");
        try{
            sender.send(msg);
            System.out.println("posalo");
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    @RequestMapping(value = "/friends/{id}")
    public List<Guest> getAllFriends(@PathVariable long id){
        Guest guest = repository.findOne(id);
        if(guest!= null)
            return guest.getFriends();
        else
            return null;
    }

    @RequestMapping(value = "/requests/{id}")
    public List<Guest> getAllRequests(@PathVariable long id){
        Guest guest = repository.findOne(id);
        if(guest!= null)
            return guest.getRequests();
        else
            return null;
    }


    @RequestMapping(value = "/addable/{id}")
    public List<Guest> getAddableFriends(@PathVariable long id){
        Guest guest = repository.findOne(id);
        List<Guest> allGuests = repository.findAll();

        ArrayList<Guest> addableGuests = new ArrayList<>();

        for(Guest g : allGuests){
            if(!g.equals(guest))
                if(!guest.getFriends().contains(g))
                    if(!guest.getRequests().contains(g))
                        if(!g.getRequests().contains(guest))
                            addableGuests.add(g);
        }
        return addableGuests;
    }






    @RequestMapping(value = "addFriend/{id}/{friend}")
    public void addFriend(@PathVariable long id, @PathVariable long friend){
        Guest guest = repository.findOne(id);
        Guest newFriend = repository.findOne(friend);
        if(guest!=null & newFriend!=null){
            List<Guest> currentFriends = guest.getFriends();
            currentFriends.add(newFriend);
            guest.setFriends(currentFriends);
            repository.save(guest);

            List<Guest> currentFriends2 = newFriend.getFriends();
            currentFriends2.add(guest);
            newFriend.setFriends(currentFriends2);
            repository.save(newFriend);
        }
    }

    @RequestMapping(value = "sendRequest/{id}/{friend}")
    public void sendRequest(@PathVariable long id, @PathVariable long friend){
        Guest guest = repository.findOne(id);
        Guest requested = repository.findOne(friend);

        if(guest != null & requested != null){
            if(!guest.getFriends().contains(requested) & !requested.getFriends().contains(guest) & !guest.getRequests().contains(requested) & !requested.getRequests().contains(guest)){
                requested.getRequests().add(guest);
                repository.save(requested);
            }
        }

    }

    @RequestMapping(value = "acceptRequest/{id}/{friend}")
    public void acceptRequest(@PathVariable long id, @PathVariable long friend){
        Guest guest = repository.findOne(id);
        Guest requested = repository.findOne(friend);
        if(guest != null & requested != null){
            if(guest.getRequests().contains(requested)) {
                guest.getRequests().remove(requested);
                if (!guest.getFriends().contains(requested)) {
                    guest.getFriends().add(requested);
                    requested.getFriends().add(guest);
                    System.out.println("Dodao sam!");
                    repository.save(guest);
                    repository.save(requested);
                }
            }
        }
    }

    @RequestMapping(value = "/change",method = RequestMethod.PUT)
    public void change(@RequestBody Guest guest){
        repository.save(guest);
    }

    @RequestMapping(value = "/changeImage/{id}", method = RequestMethod.PUT)
    public void changeImage(@PathVariable long id , @RequestBody Blob image){
        //Guest guest = repository.findOne(id);
        //guest.setImage(image);
        //repository.save(guest);
    }

    @RequestMapping(value = "declineRequest/{id}/{friend}")
    public void declineRequest(@PathVariable long id, @PathVariable long friend){
        Guest guest = repository.findOne(id);
        Guest requested = repository.findOne(friend);
        if(guest != null & requested != null){
            if(guest.getRequests().contains(requested) & !guest.getFriends().contains(requested)) {
                guest.getRequests().remove(requested);
                repository.save(guest);
            }
        }
    }

    @RequestMapping(value = "deleteFriend/{id}/{friend}")
    public void deleteFriend(@PathVariable long id, @PathVariable long friend){
        Guest guest = repository.findOne(id);
        Guest requested = repository.findOne(friend);
        if(guest != null & requested != null){
            if(guest.getFriends().contains(requested) & requested.getFriends().contains(guest)){
                guest.getFriends().remove(requested);
                requested.getFriends().remove(guest);
                repository.save(guest);
                repository.save(requested);
            }
        }

    }




}
