package com.bender.Controllers;

import com.bender.Beans.*;
import com.bender.Repositories.BillRepository;
import com.bender.Repositories.OrderItemRepository;
import com.bender.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    private StewardRepository stewardRepository;

    @Autowired
    public BillController(BillRepository repository, ReservationRepository reservationRepository, EmployeeRepository employeeRepository,
                          RestaurantRegionRepository rrepository, DailyScheduleRepository dailyScheduleRepository, OrderItemRepository orderItemRepository,
                          StewardRepository stewardRepository){
        this.repository=repository;
        this.reservationRepository=reservationRepository;
        this.employeeRepository=employeeRepository;
        this.rrepository=rrepository;
        this.dailyScheduleRepository=dailyScheduleRepository;
        this.orderItemRepository=orderItemRepository;
        this.stewardRepository=stewardRepository;
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

    @RequestMapping(value = "/izradiZaRacun/{stewardID}" , method = RequestMethod.POST)
    public void izradiZaRacun(@PathVariable long stewardID,@RequestBody Bill bill){
        Reservation res=reservationRepository.findOne(bill.getReservation().getReservation_id());
        res.setStatus("closed");
        reservationRepository.save(res);
        bill.setDate(new Date());
        Steward steward=stewardRepository.getOne(stewardID);
        //billz.setSteward(steward);

        List<OrderItem> orderItems=orderItemRepository.findAll();
        List<OrderItem> tempOI=new ArrayList<>();
        HashMap<Long,Integer> mapa=new HashMap<>();
        for(OrderItem oi: orderItems){
            if(oi.getReservation().getReservation_id()==res.getReservation_id()){
                tempOI.add(oi);
            }
        }

        Steward s1=null;
        Steward s2=null;
        int bo1=0;
        int bo2=0;
        double price=0;
        for(OrderItem oi:tempOI){
            if(oi.getStatus().equals("gostPorucio")){
                //if(oi.getSteward().getUser_id()!=steward.getUser_id()) {
                oi.setStatus("konobarPrihvatio");
                oi.setSteward(steward);
                orderItemRepository.save(oi);
                //}
            }
            price+=oi.getFoodDrinkItem().getPrice();
            System.out.println(oi.getSteward().getUser_id());
            if(s1==null){
                s1=oi.getSteward();
                bo1++;
            }else if(s1.getUser_id()!=oi.getSteward().getUser_id() && s2==null){
                s2=oi.getSteward();
                bo2++;
            }
            else if(s1.getUser_id()==oi.getSteward().getUser_id() && s1!=null){
                bo1++;
            }
            else if(s2.getUser_id()==oi.getSteward().getUser_id() && s2!=null){
                bo2++;
            }
        }
        System.out.println(price);
        bill.setPrice(price);

        if(bo2>=bo1){
            steward=stewardRepository.findOne(s2.getUser_id());
        }
        else
            steward=stewardRepository.findOne(s1.getUser_id());

        bill.setSteward(steward);
        System.out.println(steward.getUser_id());

        bill.setReservation(res);
        repository.save(bill);
    }

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
