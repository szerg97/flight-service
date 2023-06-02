package com.szalai.flightservice.controller;

import com.szalai.flightservice.service.FlightBookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/flight-service")
public class FlightBookingController {

    private final FlightBookingService flightBookingService;

    public FlightBookingController(FlightBookingService flightBookingService) {
        this.flightBookingService = flightBookingService;
    }

    @PostMapping("")
    public FlightBookingResponse book(
            @RequestBody FlightBookingRequest request
    ){
        return flightBookingService.bookFlight(request);
    }
}
