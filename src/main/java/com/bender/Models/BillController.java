package com.bender.Models;

import com.bender.Beans.*;
import com.bender.Repositories.DailyScheduleRepository;
import com.bender.Repositories.EmployeeRepository;
import com.bender.Repositories.ReservationRepository;
import com.bender.Repositories.RestaurantRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 3/1/2017.
 */
@RestController
@RequestMapping(value ="/bills")
public class BillController {

    private BillRepository repository;
    private ReservationRepository reservationRepository;
    private EmployeeRepository employeeRepository;
    private RestaurantRegionRepository rrepository;
    private DailyScheduleRepository dailyScheduleRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public BillController(BillRepository repository, ReservationRepository reservationRepository, EmployeeRepository employeeRepository,
                          RestaurantRegionRepository rrepository, DailyScheduleRepository dailyScheduleRepository, OrderItemRepository orderItemRepository){
        this.repository=repository;
        this.reservationRepository=reservationRepository;
        this.employeeRepository=employeeRepository;
        this.rrepository=rrepository;
        this.dailyScheduleRepository=dailyScheduleRepository;
        this.orderItemRepository=orderItemRepository;
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<Bill> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody Bill newBill){
        repository.save(newBill);
    }

    @RequestMapping(value = "/findOne/{id}")
    public Bill getById(@PathVariable long id){
        return repository.findOne(id);
    }

    @RequestMapping(value="/update", method=RequestMethod.PUT)
    public void update(@RequestBody Bill updatedBill){repository.save(updatedBill);}

    @RequestMapping(value = "/zahtevZaRacun" , method = RequestMethod.PUT)
    public void zahtevZaRacun(@RequestBody Reservation reservation){
        Reservation res=reservationRepository.findOne(reservation.getReservation_id());
        res.setStatus("active");
        reservationRepository.save(res);
        Bill bill=new Bill();
        bill.setReservation(res);
        repository.save(bill);
    }

    /*@RequestMapping(value = "/izradiRacun" , method = RequestMethod.PUT)
    public void izradiZaRacun(@RequestBody Bill bill){
        Reservation res=reservationRepository.findOne(reservation.getReservation_id());
        res.setStatus("closed");
        reservationRepository.save(res);
        Bill bill=new Bill();
        bill.setReservation(res);
        repository.save(bill);
    }*/

    @RequestMapping(value="/findBillsForSteward/{regionID}", method=RequestMethod.POST)
    public List<Bill> getBFS(@PathVariable long regionID, @RequestBody Steward steward){
        List<Bill> bills=repository.findAll();

        List<Bill> temp=new ArrayList<>();
        for(Bill bill: bills){
            Reservation reservation=bill.getReservation();
            RestaurantRegion RR=reservation.getTables().get(0).getRestaurantRegion();
            if(RR.equals(rrepository.findOne(regionID))) {
                if (reservation.getStatus().equals("active") || reservation.getStatus().equals("closed")) {
                    temp.add(bill);
                }
            }
        }

        return temp;
    }
}
