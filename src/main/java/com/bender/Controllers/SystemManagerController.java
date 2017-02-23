package com.bender.Controllers;

import com.bender.Beans.SystemManager;
import com.bender.Repositories.SystemManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nikola on 16-02-17.
 */
@RestController
@RequestMapping(value="/systemmanagers")
public class SystemManagerController {

    private SystemManagerRepository repository;

    @Autowired
    public SystemManagerController(SystemManagerRepository repository){
        this.repository=repository;
    }
}
