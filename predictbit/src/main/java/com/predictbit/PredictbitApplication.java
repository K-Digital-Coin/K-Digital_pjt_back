package com.predictbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PredictbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(PredictbitApplication.class, args);
    }

}
