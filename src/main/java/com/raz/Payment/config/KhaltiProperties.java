package com.raz.Payment.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "khalti")
public class KhaltiProperties {

    private String secretKey;
    private String initiateUrl;
}
