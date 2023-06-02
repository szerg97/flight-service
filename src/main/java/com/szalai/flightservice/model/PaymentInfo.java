package com.szalai.flightservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfo {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    private Long passengerId;
    private String accountNumber;
    private double amount;
    private String cardType;
}
