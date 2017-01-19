package com.bla.controllers;

import com.bla.entities.Automobile;
import com.bla.entities.Driver;
import com.bla.entities.User;
import com.bla.forms.UserForm;
import com.bla.services.INTERFACES.AutosService;
import com.bla.services.INTERFACES.DriversService;
import com.bla.services.INTERFACES.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

/**
 * Created by softi on 16.01.2017.
 */
@Controller
@EnableAutoConfiguration
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    DriversService driversService;

    @Autowired
    AutosService autosService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/login")
    public String loginPage(ModelMap modelMap, Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registrationGet(ModelMap modelMap, Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }
        modelMap.put("regForm", new UserForm());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPost(@ModelAttribute("user") UserForm regForm, BindingResult bindingResult,
                                   ModelMap modelMap) throws IOException {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (usersService.findByNickname(regForm.getNickname()) != null) {
            modelMap.put("error", "Пользователь с таким именем уже существует");
            return "registration";
        }
        if (!regForm.getPassword().equals(regForm.getPasswordConfirmation())) {
            modelMap.put("error", "Подтверждение пароля не то");
            return "registration";
        }
        User user = new User();
        user.setNickname(regForm.getNickname());
        user.setFirstname(regForm.getFirstname());
        user.setSurname(regForm.getSurname());
        String cryptPassword = encoder.encode(regForm.getPassword());
        user.setPassword(cryptPassword);
        user.setEmail(regForm.getEmail());
        user.setRole("USER");



//        if (!avatar.isEmpty()) {
//            String filename = saveImage(avatar);
//            user.setAvatar(filename);
//        } else {
//            user.setAvatar("default.jpg");
//        }

        usersService.addUser(user);

        return "redirect:/login";
    }

    @RequestMapping(value = "/users/{user_id:\\d+}", method = RequestMethod.GET)
    public String profile(ModelMap modelMap, @PathVariable int user_id, Principal principal) {
//        boolean access = false;
//        if (principal != null) {
//            User pUser = (User) ((Authentication) principal).getPrincipal();
//            if (pUser.getId() == user_id || "ROLE_ADMIN".equals(pUser.getRole())) {
//                access = true;
//            }
//        }
        User user = usersService.findById(user_id);
        modelMap.put("userinfo", user);
//        modelMap.put("access", access);
//        modelMap.put("talons", user.getTalons());
        return "profile";
    }

    @RequestMapping(value = "/newauto", method = RequestMethod.GET)
    public String newAutoPage() {
        return "newauto";
    }

    @RequestMapping(value = "/newauto", method = RequestMethod.POST)
    public String newAuto(@ModelAttribute(name = "auto") Automobile automobile, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Driver driver = user.getDriver();
        if (driver == null) {
            driver = new Driver();
            driver.setUser(user);
            driver.setRating(0);
            driver.setAutomobileList(new ArrayList<>());
            driver.setExperience(0);
            driversService.addDriver(driver);
        }
        automobile.setDriver(driver);
        autosService.addAuto(automobile);
        return "redirect:/users/" + user.getId();
    }





}
