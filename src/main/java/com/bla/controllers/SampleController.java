package com.bla.controllers;

import com.bla.services.INTERFACES.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @Autowired
    UsersService usersService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World! adca";
    }

    @RequestMapping("/users")
    @ResponseBody
    String users() {
        return usersService.findAll().toString();
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sample() {
        return "sample";
    }

//    public static void main(String[] args) throws Exception {
//            SpringApplication.run(SampleController.class, args);
//    }
}