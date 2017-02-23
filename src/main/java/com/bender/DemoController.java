package com.bender;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Bender on 12/18/2016.
 */
@RestController
public class DemoController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello world!";
    }
}
