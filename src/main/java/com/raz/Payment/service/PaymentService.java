package com.raz.Payment.service;

import com.raz.Payment.config.KhaltiProperties;
import com.raz.Payment.dto.InitiatePaymentRequest;
import com.raz.Payment.dto.InitiatePaymentResponse;
import com.raz.Payment.dto.KhaltiInitiateRequest;
import com.raz.Payment.dto.KhaltiInitiateResponse;
import com.raz.Payment.entity.Payment;
import com.raz.Payment.repo.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestClient restClient;
    private final KhaltiProperties khaltiProperties;

    public Payment createPayment(InitiatePaymentRequest request) {

        Payment payment = Payment.builder()
                .orderId(UUID.randomUUID().toString())
                .amount(request.getAmount())
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .status("PENDING")
                .build();

        return paymentRepository.save(payment);
    }

    public InitiatePaymentResponse initiatePayment(InitiatePaymentRequest request) {
        Payment payment = createPayment(request);

        KhaltiInitiateRequest khaltiRequest =
                KhaltiInitiateRequest.builder()
                        .return_url("http://localhost:8080/api/payments/callback")
                        .website_url("http://localhost:8080")
                        .amount(payment.getAmount().longValue())
                        .purchase_order_id(payment.getOrderId())
                        .purchase_order_name("Demo Payment")
                        .build();

        KhaltiInitiateResponse khaltiResponse =
                restClient.post()
                        .uri(khaltiProperties.getInitiateUrl())
                        .header(
                                "Authorization",
                                "Key " + khaltiProperties.getSecretKey()
                        )
                        .body(khaltiRequest)
                        .retrieve()
                        .body(KhaltiInitiateResponse.class);

        payment.setPidx(khaltiResponse.getPidx());
        paymentRepository.save(payment);

        return InitiatePaymentResponse.builder()
                .orderId(payment.getOrderId())
                .paymentUrl(khaltiResponse.getPayment_url())
                .build();
    }

}