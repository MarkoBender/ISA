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
        "/"
    })
    public String index(Model model){
        return "index";
    }
}
