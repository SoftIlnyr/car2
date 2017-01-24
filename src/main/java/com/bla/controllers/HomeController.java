package com.bla.controllers;

import com.bla.services.INTERFACES.DriversService;
import com.bla.services.INTERFACES.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class HomeController {

    @Autowired
    UsersService usersService;

    @Autowired
    DriversService driversService;

    @RequestMapping("/")
    String home(ModelMap modelMap) {
        modelMap.put("drivers", driversService.getBest());
        return "home";
    }



//    @RequestMapping("/users")
//    @ResponseBody
//    String users() {
//        return usersService.findAll().toString();
//    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sample() {
        return "home";
    }

//    public static void main(String[] args) throws Exception {
//            SpringApplication.run(SampleController.class, args);
//    }
}