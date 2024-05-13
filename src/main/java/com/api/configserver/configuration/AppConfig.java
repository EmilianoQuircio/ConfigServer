package com.api.configserver.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "app")
@Data
public class AppConfig {

    private String name;
    private String powered;
    private String date;

}
