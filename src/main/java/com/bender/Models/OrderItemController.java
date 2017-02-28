package com.bender.Models;

import com.bender.Beans.*;
import com.bender.Repositories.*;
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
    private DailyScheduleRepository dailyScheduleRepository;
    private DishRepository dishRepository;
    private DrinkRepository drinkRepository;
    private StewardRepository stewardRepository;
    private EmployeeRepository employeeRepository;
    private BarmanRepository barmanRepository;
    private CookRepository cookRepository;

    @Autowired
    public OrderItemController (OrderItemRepository repository, RestaurantRegionRepository rrrepository, DishRepository dishRepository,
                                DrinkRepository drinkRepository, StewardRepository stewardRepository, DailyScheduleRepository dailyScheduleRepository,
                                EmployeeRepository employeeRepository, BarmanRepository barmanRepository, CookRepository cookRepository){
        this.repository=repository;
        this.employeeRepository=employeeRepository;
        this.stewardRepository=stewardRepository;
        this.barmanRepository=barmanRepository;
        this.cookRepository=cookRepository;
        this.dailyScheduleRepository=dailyScheduleRepository;
        this.rrrepository=rrrepository;
        this.dishRepository=dishRepository;
        this.drinkRepository=drinkRepository;
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
            else if(oi.getSteward().getUser_id()==steward.getUser_id()){
                System.out.println("USAO JEBENO USAO JEBENO");
                tempOIS.add(oi);
            }
            else{
                Employee trenutni=employeeRepository.findOne(oi.getSteward().getUser_id());

                Date date=new Date();

                List<DailySchedule> kada_radi=new ArrayList<>();
                for(DailySchedule ds : dailyScheduleRepository.findAll()){
                    if(ds.getEmployee().equals(trenutni)){
                        int endHours=ds.getEndHours();
                        if(endHours==0)
                            endHours=24;

                        if(ds.getStartday().getTime()<=date.getTime() && ds.getEndday().getTime()>=date.getTime() && ds.getStartHours()<=date.getHours() && endHours>=date.getHours()) {
                            boolean potencijalno_dobro = true;
                            if (ds.getStartHours() == date.getHours()) {
                                if (ds.getStartMinutes() > date.getMinutes()) {
                                    potencijalno_dobro = false;
                                }
                            }
                            if (potencijalno_dobro) {
                                if (ds.getEndHours() == date.getHours()) {
                                    if (ds.getEndMinutes() < date.getMinutes()) {
                                        potencijalno_dobro = false;
                                    }
                                }
                            }
                            if (!potencijalno_dobro) {
                                tempOIS.add(oi);
                                //break;
                            }
                        }else
                            tempOIS.add(oi);
                    }
                }
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

    @RequestMapping(value="/findOrderItemForCook", method=RequestMethod.POST)
    public List<OrderItem> getOIFS(@RequestBody Cook cook){
        List<OrderItem> ois=repository.findAll();
        List<OrderItem> tempOIS=new ArrayList<>();

        for(OrderItem oi: ois){
            if(oi.getCook() == null || oi.getCook().getUser_id()==cook.getUser_id()){
                tempOIS.add(oi);
            }
            else{
                Employee trenutni=employeeRepository.findOne(oi.getCook().getUser_id());

                Date date=new Date();

                List<DailySchedule> kada_radi=new ArrayList<>();
                for(DailySchedule ds : dailyScheduleRepository.findAll()){
                    if(ds.getEmployee().equals(trenutni)){
                        int endHours=ds.getEndHours();
                        if(endHours==0)
                            endHours=24;

                        if(ds.getStartday().getTime()<=date.getTime() && ds.getEndday().getTime()>=date.getTime() && ds.getStartHours()<=date.getHours() && endHours>=date.getHours()) {
                            boolean potencijalno_dobro = true;
                            if (ds.getStartHours() == date.getHours()) {
                                if (ds.getStartMinutes() > date.getMinutes()) {
                                    potencijalno_dobro = false;
                                }
                            }
                            if (potencijalno_dobro) {
                                if (ds.getEndHours() == date.getHours()) {
                                    if (ds.getEndMinutes() < date.getMinutes()) {
                                        potencijalno_dobro = false;
                                    }
                                }
                            }
                            if (!potencijalno_dobro) {
                                tempOIS.add(oi);
                                //break;
                            }
                        }else
                            tempOIS.add(oi);
                    }
                }
            }
        }

        List<OrderItem> temp=new ArrayList<>();
        for(OrderItem res : tempOIS){
            Reservation reservation=res.getReservation();

            Date datum = reservation.getDateTime();
            Date resDate=new Date(datum.getTime());
            resDate.setHours(resDate.getHours() + reservation.getDuration());
            if (resDate.after(new Date()) && reservation.getDateTime().before(new Date())) {
                FoodDrinkItem foodDrinkItem=res.getFoodDrinkItem();
                if(foodDrinkItem.getTip().equals("Food")) {
                    Dish dish=dishRepository.findOne(foodDrinkItem.getFoodDrinkItem_id());
                    if(dish.getDish_type().getDishtype_id()==cook.getDishType().getDishtype_id()) {
                        if(res.getStatus().equals("konobarPrihvatio") || res.getStatus().equals("kuvarPrihvatio") || res.getStatus().equals("kuvarSpremio"))
                            temp.add(res);
                    }
                }
            }
        }
        return temp;
    }

    @RequestMapping(value="/findOrderItemForBarman", method=RequestMethod.POST)
    public List<OrderItem> getOIFS(@RequestBody Barman barman){
        List<OrderItem> ois=repository.findAll();
        List<OrderItem> tempOIS=new ArrayList<>();

        for(OrderItem oi: ois){
            if(oi.getBarman() == null || oi.getBarman().getUser_id()==barman.getUser_id()){
                tempOIS.add(oi);
            }
            else{
                Employee trenutni=employeeRepository.findOne(oi.getBarman().getUser_id());

                Date date=new Date();

                List<DailySchedule> kada_radi=new ArrayList<>();
                for(DailySchedule ds : dailyScheduleRepository.findAll()){
                    if(ds.getEmployee().equals(trenutni)){
                        int endHours=ds.getEndHours();
                        if(endHours==0)
                            endHours=24;

                        if(ds.getStartday().getTime()<=date.getTime() && ds.getEndday().getTime()>=date.getTime() && ds.getStartHours()<=date.getHours() && endHours>=date.getHours()) {
                            boolean potencijalno_dobro = true;
                            if (ds.getStartHours() == date.getHours()) {
                                if (ds.getStartMinutes() > date.getMinutes()) {
                                    potencijalno_dobro = false;
                                }
                            }
                            if (potencijalno_dobro) {
                                if (ds.getEndHours() == date.getHours()) {
                                    if (ds.getEndMinutes() < date.getMinutes()) {
                                        potencijalno_dobro = false;
                                    }
                                }
                            }
                            if (!potencijalno_dobro) {
                                tempOIS.add(oi);
                                //break;
                            }
                        }else
                            tempOIS.add(oi);
                    }
                }
            }
        }

        List<OrderItem> temp=new ArrayList<>();
        for(OrderItem res : tempOIS){
            Reservation reservation=res.getReservation();

            Date datum = reservation.getDateTime();
            Date resDate=new Date(datum.getTime());
            resDate.setHours(resDate.getHours() + reservation.getDuration());
            System.out.println("datum kraja je "+resDate+" a datum pocetka je "+reservation.getDateTime());
            if (resDate.after(new Date()) && reservation.getDateTime().before(new Date())) {
                FoodDrinkItem foodDrinkItem=res.getFoodDrinkItem();
                if(foodDrinkItem.getTip().equals("Drink")) {
                    if(res.getStatus().equals("konobarPrihvatio") || res.getStatus().equals("sankerPrihvatio") || res.getStatus().equals("sankerSpremio"))
                        temp.add(res);
                }
            }
        }
        return temp;
    }

    @RequestMapping(value = "/konobarPrihvati/{stewardID}" , method = RequestMethod.PUT)
    public void konobarPrihvati(@RequestBody OrderItem updatedOI, @PathVariable long stewardID){
        updatedOI.setSteward(stewardRepository.findOne(stewardID));
        updatedOI.setStatus("konobarPrihvatio");
        repository.save(updatedOI);
    }

    @RequestMapping(value = "/konobarDostavi" , method = RequestMethod.PUT)
    public void konobarDostavi(@RequestBody OrderItem updatedOI){
        updatedOI.setStatus("dostavljeno");
        repository.save(updatedOI);
    }

    @RequestMapping(value = "/kuvarPrihvati/{cookID}" , method = RequestMethod.PUT)
    public void kuvarPrihvati(@RequestBody OrderItem updatedOI, @PathVariable long cookID){
        updatedOI.setCook(cookRepository.findOne(cookID));
        updatedOI.setStatus("kuvarPrihvatio");
        repository.save(updatedOI);
    }

    @RequestMapping(value = "/kuvarSpremi" , method = RequestMethod.PUT)
    public void kuvarSpremi(@RequestBody OrderItem updatedOI){
        updatedOI.setStatus("kuvarSpremio");
        repository.save(updatedOI);
    }

    @RequestMapping(value = "/sankerPrihvati/{barmanID}" , method = RequestMethod.PUT)
    public void sankerPrihvati(@RequestBody OrderItem updatedOI, @PathVariable long barmanID){
        updatedOI.setBarman(barmanRepository.findOne(barmanID));
        updatedOI.setStatus("sankerPrihvatio");
        repository.save(updatedOI);
    }

    @RequestMapping(value = "/sankerSpremi" , method = RequestMethod.PUT)
    public void sankerSpremi(@RequestBody OrderItem updatedOI){
        updatedOI.setStatus("sankerSpremio");
        repository.save(updatedOI);
    }



}
