package com.szalai.flightservice.util;

import java.util.HashMap;
import java.util.Map;

public class PaymentUtils {

    private static final Map<String, Double> paymentMap = new HashMap<>();

    static {
        paymentMap.put("acc1", 12000.0);
        paymentMap.put("acc2", 10000.0);
        paymentMap.put("acc3", 50000.0);
        paymentMap.put("acc4", 5000.0);
    }

    public static boolean validateCreditLimit(String accNo, double amount){
        if (amount > paymentMap.get(accNo)){
            throw new IllegalStateException("Insufficient found on account " + accNo + "(" + paymentMap.get(accNo) + ")");
        }
        return true;
    }
}
