package com.Booking.com.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

        //       http://localhost:8083/api/v1/countries/addCountry

    @PostMapping("/addCountry")
    public String addCountry(){
        return "done";
    }
}
