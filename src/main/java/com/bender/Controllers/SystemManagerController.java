package com.bender.Controllers;

import com.bender.Beans.Restaurant;
import com.bender.Beans.RestaurantManager;
import com.bender.Beans.SystemManager;
import com.bender.Repositories.SystemManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nikola on 16-02-17.
 */
@RestController
@RequestMapping(value="/systemManagers")
public class SystemManagerController {

    private SystemManagerRepository repository;

    @Autowired
    public SystemManagerController(SystemManagerRepository repository){
        this.repository=repository;
        /*SystemManager sysm=new SystemManager();
        sysm.setEmail("admin@live.com");
        sysm.setPassword("123");
        sysm.setName("Admin");
        sysm.setSurname("Adminic");
        sysm.setUloga("SystemManager");
        this.repository.save(sysm);*/
    }

    @RequestMapping(value = "/all")
    public List<SystemManager> getAll(){
        return repository.findAll();
    }

    @RequestMapping(value = "/create" , method = RequestMethod.PUT)
    public void create(@RequestBody SystemManager newSystemManager){
        newSystemManager.setUloga("SystemManager");
        repository.save(newSystemManager);
    }

    @RequestMapping(value = "/findOne/{id}")
    public SystemManager getSystemManager(@PathVariable long id) {
        SystemManager restaurantManager = repository.findOne(id);
        return restaurantManager;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody SystemManager updatedSystemManager) {
        SystemManager systemManager=updatedSystemManager;
        repository.save(systemManager);
    }
}
