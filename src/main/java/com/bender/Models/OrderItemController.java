package com.bender.Models;

import com.bender.Beans.*;
import com.bender.Repositories.DailyScheduleRepository;
import com.bender.Repositories.RestaurantRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2/28/2017.
 */
@RestController
@RequestMapping(value ="/orderItems")
public class OrderItemController {

    private OrderItemRepository repository;
    private RestaurantRegionRepository rrrepository;

    @Autowired
    public OrderItemController (OrderItemRepository repository, RestaurantRegionRepository rrrepository){
        this.repository=repository;
        this.rrrepository=rrrepository;
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public List<OrderItem> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody OrderItem newOI){
        repository.save(newOI);
    }

    @RequestMapping(value = "/findOne/{id}")
    public OrderItem getById(@PathVariable long id){
        return repository.findOne(id);
    }

    @RequestMapping(value="/findOrderItemForSteward/{regionID}", method=RequestMethod.POST)
    public List<OrderItem> getOIFS(@PathVariable long regionID, @RequestBody Steward steward){
        List<OrderItem> ois=repository.findAll();
        List<OrderItem> tempOIS=new ArrayList<>();

        for(OrderItem oi: ois){
            if(oi.getSteward() == null){
                tempOIS.add(oi);
            }
            else if(oi.getSteward().equals(steward)){
                tempOIS.add(oi);
            }
        }

        System.out.println("DUZINA TEMPOIS JE "+tempOIS.size());

        List<OrderItem> temp=new ArrayList<>();
        for(OrderItem res : tempOIS){
            System.out.println("USAO U PETLJU");
            Reservation reservation=res.getReservation();
            RestaurantRegion RR=reservation.getTables().get(0).getRestaurantRegion();
            if(RR.equals(rrrepository.findOne(regionID))) {
                System.out.println("ISTA JE ZONA SA IMENOM "+RR.getName());
                Date datum = reservation.getDateTime();
                Date resDate=new Date(datum.getTime());
                resDate.setHours(resDate.getHours() + reservation.getDuration());
                System.out.println("datum kraja je "+resDate+" a datum pocetka je "+reservation.getDateTime());
                if (resDate.after(new Date()) && reservation.getDateTime().before(new Date())) {
                    temp.add(res);
                }
            }
        }
        return temp;
    }
}
