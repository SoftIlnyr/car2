package com.bla.controllers;

import com.bla.entities.*;
import com.bla.forms.TripForm;
import com.bla.services.INTERFACES.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@EnableAutoConfiguration
public class TripsController {
    @Autowired
    TripsService tripsService;


    @Autowired
    BookingService bookingService;

    @Autowired
    ReviewsService reviewsService;

    @Autowired
    DriversService driversService;

    @Autowired
    AutosService autosService;

    @Autowired
    PassengersService passengersService;

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

    @RequestMapping(value = "/trips/{trip_id:\\d+}/status", method = RequestMethod.GET)
    public String tripStatusPage(@PathVariable int trip_id, ModelMap modelMap) {
        Trip trip = tripsService.findById(trip_id);
        modelMap.put("trip", trip);
        return "tripstatus";
    }

    @RequestMapping(value = "/trips/{trip_id:\\d+}/status", method = RequestMethod.POST)
    public String tripStatus(@PathVariable int trip_id, @RequestParam String status, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Trip trip = tripsService.findById(trip_id);
        trip.setStatus(status);
        tripsService.update(trip);
        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/trips/{trip_id:\\d+}/review", method = RequestMethod.GET)
    public String tripReviewPage(@PathVariable int trip_id, ModelMap modelMap, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Trip trip = tripsService.findById(trip_id);
        modelMap.put("trip", trip);
        return "tripreview";
    }

    @RequestMapping(value = "/trips/{trip_id:\\d+}/review", method = RequestMethod.POST)
    public String tripReview(@PathVariable int trip_id, @ModelAttribute(name = "review") Review review,
                             Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Trip trip = tripsService.findById(trip_id);
        review.setUser(user);
        review.setTrip(trip);
        reviewsService.addReview(review);
        if (user.getDriver() != null && trip.getDriver().getUser().getId() == user.getId()) {
            for (Passenger passenger : trip.getPassengers()) {
                passenger.setRating(passenger.getRating() + review.getGrade());
                passengersService.update(passenger);
            }

        } else {
            for (Passenger passenger : trip.getPassengers()) {
                if (passenger.getUser().getId() == user.getId()) {
                    Driver driver = trip.getDriver();
                    driver.setRating(driver.getRating() + review.getGrade());
                    driversService.update(driver);
                    break;
                }
            }

        }

        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public String TripsPage(ModelMap modelMap) {
        List<Trip> trips = tripsService.findByStatusOrderDate();
        modelMap.put("trips", trips);
        return "trips";
    }

    @RequestMapping(value = "/trips/find", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Trip> getTrips(HttpServletRequest request) {
        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");
        List<Trip> trips = tripsService.findBySearch(departure, destination);
        return trips;
    }
}

