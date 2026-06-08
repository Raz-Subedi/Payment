package com.raz.Payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InitiatePaymentRequest {

    private BigDecimal amount;

    private String customerName;

    private String customerEmail;
}
