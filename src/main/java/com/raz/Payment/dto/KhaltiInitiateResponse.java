package com.raz.Payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KhaltiInitiateResponse {

    private String pidx;
    private String payment_url;
    private String expires_at;
    private Long expires_in;
}
