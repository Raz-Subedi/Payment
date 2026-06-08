package com.raz.Payment.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitiatePaymentResponse {

    private String orderId;
    private String paymentUrl;
}
