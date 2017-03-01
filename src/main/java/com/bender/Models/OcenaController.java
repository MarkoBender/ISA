package com.bender.Models;

import com.bender.Beans.Guest;
import com.bender.Beans.Reservation;
import com.bender.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 3/1/2017.
 */
@RestController
@RequestMapping(value ="/ocene")
public class OcenaController {

    private OcenaRepository repository;
    private ReservationRepository reservationRepository;

    @Autowired
    public OcenaController (OcenaRepository repository, ReservationRepository reservationRepository){this.repository=repository; this.reservationRepository=reservationRepository;}

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
}
