package com.szalai.flightservice.service;

import com.szalai.flightservice.controller.FlightBookingRequest;
import com.szalai.flightservice.controller.FlightBookingResponse;
import com.szalai.flightservice.exception.InsufficientFundsException;
import com.szalai.flightservice.model.PassengerInfo;
import com.szalai.flightservice.model.PaymentInfo;
import com.szalai.flightservice.repository.PassengerInfoRepository;
import com.szalai.flightservice.repository.PaymentInfoRepository;
import com.szalai.flightservice.util.PaymentUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FlightBookingService {

    private final PassengerInfoRepository passengerInfoRepository;
    private final PaymentInfoRepository paymentInfoRepository;

    public FlightBookingService(PassengerInfoRepository passengerInfoRepository, PaymentInfoRepository paymentInfoRepository) {
        this.passengerInfoRepository = passengerInfoRepository;
        this.paymentInfoRepository = paymentInfoRepository;
    }

    @Transactional(rollbackFor = {InsufficientFundsException.class})
    public FlightBookingResponse bookFlight(FlightBookingRequest request){
        PassengerInfo passengerInfo = request.passengerInfo();
        PaymentInfo paymentInfo = request.paymentInfo();

        passengerInfoRepository.save(passengerInfo);

        PaymentUtils.validateCreditLimit(
                paymentInfo.getAccountNumber(),
                passengerInfo.getFare());

        paymentInfo.setPassengerId(passengerInfo.getId());
        paymentInfo.setAmount(passengerInfo.getFare());
        paymentInfoRepository.save(paymentInfo);

        return new FlightBookingResponse(
                "SUCCESS",
                passengerInfo.getFare(),
                passengerInfo
        );
    }
}
