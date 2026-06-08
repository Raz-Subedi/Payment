package com.raz.Payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KhaltiInitiateRequest {

    private String return_url;

    private String website_url;

    private Long amount;

    private String purchase_order_id;

    private String purchase_order_name;
}