package com.bla.controllers;

import com.bla.entities.*;
import com.bla.forms.TripForm;
import com.bla.forms.UserForm;
import com.bla.services.INTERFACES.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    PassengersService passengersService;

    @Autowired
    TripsService tripsService;

    @Autowired
    BookingService bookingService;

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

        Passenger passenger = new Passenger();
        passenger.setUser(user);
        passenger.setRating(0);

        passengersService.addPassenger(passenger);


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
        User user = (User) ((Authentication) principal).getPrincipal();
        User userinfo = usersService.findById(user_id);
        modelMap.put("userinfo", userinfo);
        if (userinfo.getDriver() != null && userinfo.getDriver().getTrips().size() > 0) {
            List<Trip> tripList = userinfo.getDriver().getTrips();
            List<Trip> driverTrips = new ArrayList<>();
            for (Trip trip : tripList) {
                if (trip.getStatus().contains("Ожидание")) {
                    driverTrips.add(trip);
                }
            }
            modelMap.put("driverTrips", driverTrips);

        }

        if (userinfo.getPassenger().getTrips().size() > 0) {

            List<Trip> tripList = userinfo.getPassenger().getTrips();
            List<Trip> pasTrips = new ArrayList<>();
            for (Trip trip : tripList) {
                if (trip.getStatus().contains("Ожидание")) {
                    pasTrips.add(trip);
                }
            }
            modelMap.put("pasTrips", pasTrips);
        }

        if (user.getId() == userinfo.getId() && user.getDriver() != null) {
            List<Trip> tripList = userinfo.getDriver().getTrips();
            List<Trip> driverTrips = new ArrayList<>();
            for (Trip trip : tripList) {
                if (trip.getStatus().contains("Ожидание")) {
                    driverTrips.add(trip);
                }
            }
            List<Booking> bookings = new ArrayList<>();
            for (Trip trip : driverTrips) {
                for (Booking booking : trip.getBookings()) {
                    if (booking.getConfirm() == null) {
                        bookings.add(booking);
                    }
                }
            }
            modelMap.put("bookings", bookings);
        }

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

    @RequestMapping(value = "/newtrip", method = RequestMethod.GET)
    public String newTripPage(ModelMap modelMap, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        List<Automobile> automobileList = user.getDriver().getAutomobileList();
        modelMap.put("automobileList", automobileList);
        return "newtrip";
    }

    @RequestMapping(value = "/newtrip", method = RequestMethod.POST)
    public String newTrip(@ModelAttribute(name = "trip") TripForm tripForm, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = null;
        try {
            date = formatter.parse(tripForm.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String autoid = tripForm.getAuto().split(" ")[0];
        int id = Integer.parseInt(autoid);

        Trip trip = new Trip();
        trip.setDriver(user.getDriver());
        trip.setAuto(autosService.findById(id));
        trip.setDeparture(tripForm.getDeparture());
        trip.setDestination(tripForm.getDestination());
        trip.setDate(date);
        trip.setPrice(tripForm.getPrice());
        trip.setCount(tripForm.getCount());
        trip.setStatus("Ожидание");
        trip.setInfo(tripForm.getInfo());
        trip.setPassengers(new ArrayList<>());

        tripsService.addTrip(trip);

        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/trips/{trip_id:\\d+}", method = RequestMethod.GET)
    public String joinTripPage(ModelMap modelMap, @PathVariable int trip_id) {
        Trip trip = tripsService.findById(trip_id);
        modelMap.put("trip", trip);
        return "jointrip";
    }

    @RequestMapping(value = "/trips/{trip_id:\\d+}", method = RequestMethod.POST)
    public String JoinTrip(@ModelAttribute(name = "booking") Booking booking, @PathVariable int trip_id, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        booking.setTrip(tripsService.findById(trip_id));
        booking.setPassenger(user.getPassenger());
        bookingService.addBooking(booking);
        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/bookings/{booking_id:\\d+}/conf", method = RequestMethod.GET)
    public String confirmBooking(@PathVariable int booking_id, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Booking booking = bookingService.findById(booking_id);
        booking.setConfirm("yes");
        Trip trip = booking.getTrip();
        trip.getPassengers().add(booking.getPassenger());
        trip.setCount(trip.getCount() - booking.getCount());
        tripsService.update(trip);
        bookingService.update(booking);
        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/bookings/{booking_id:\\d+}/deny", method = RequestMethod.GET)
    public String denyBooking(@PathVariable int booking_id, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Booking booking = bookingService.findById(booking_id);
        booking.setConfirm("no");
        bookingService.update(booking);
        return "redirect:/users/" + user.getId();
    }


}
