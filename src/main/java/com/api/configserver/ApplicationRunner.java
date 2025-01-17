package com.api.configserver;

import com.api.configserver.configuration.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private AppConfig appConfig;

    @Override
    public void run(String... args) throws Exception {

        log.info("Start CommandLineRunner...");
        log.info("Powered by {} in {}!", appConfig.getPowered(), appConfig.getDate());
        log.info("...");
        log.info("Ora puoi avviare JWTokenArticoliShop2.0!");
    }

}
