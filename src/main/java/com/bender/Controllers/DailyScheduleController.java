package com.bender.Controllers;

import com.bender.Beans.DailySchedule;
import com.bender.Repositories.DailyScheduleRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
