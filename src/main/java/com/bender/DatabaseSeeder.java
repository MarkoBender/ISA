package com.bender;

import groovy.util.logging.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Bender on 12/18/2016.
 */
@Component
public class DatabaseSeeder implements CommandLineRunner {

    private RestoranRepository repository;

    @Autowired
    public DatabaseSeeder(RestoranRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {


    }
}
