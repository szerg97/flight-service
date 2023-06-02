package com.szalai.flightservice.controller;

import com.szalai.flightservice.model.PassengerInfo;
import com.szalai.flightservice.model.PaymentInfo;

public record FlightBookingResponse(
        String status,
        double totalFare,
        PassengerInfo passengerInfo
) {
}
