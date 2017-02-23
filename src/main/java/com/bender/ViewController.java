package com.bender;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by Bender on 12/18/2016.
 */
@Controller
public class ViewController {


    @RequestMapping({
        "/",
        "/cars",
        "/gallery",
        "/tracks",
        "/tracks/{id:\\w+}",
        "/location",
        "/about"
    })
    public String index(Model model){
        model.addAttribute("username", "Boro Krnjez");
        model.addAttribute("date", new Date());
        return "index";
    }
}
