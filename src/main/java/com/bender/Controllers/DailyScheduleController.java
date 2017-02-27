package com.bender.Controllers;

import com.bender.Beans.DailySchedule;
import com.bender.Beans.Employee;
import com.bender.Repositories.DailyScheduleRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikola on 27-02-17.
 */
@RestController
@RequestMapping("/schedules")
public class DailyScheduleController {

    private DailyScheduleRepository repository;

    public DailyScheduleController(DailyScheduleRepository repository){this.repository =repository;}

    @RequestMapping(value = "/all")
    public List<DailySchedule> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody DailySchedule newSchedule){
        repository.save(newSchedule);
    }

    @RequestMapping(value = "/forEmployee", method = RequestMethod.POST)
    public List<DailySchedule> getAllForEmployee(@RequestBody Employee employee){

        List<DailySchedule> dss=repository.findAll();

        List<DailySchedule> ss=new ArrayList<>();

        for(DailySchedule ds: dss){
            if(employee.getUser_id()==ds.getEmployee().getUser_id()){
                ss.add(ds);
            }
        }

        System.out.println(dss.size());
        System.out.println(ss.size());

        return ss;
    }
}
