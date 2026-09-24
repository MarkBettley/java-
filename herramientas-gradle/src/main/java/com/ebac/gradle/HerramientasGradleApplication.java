package com.ebac.gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HerramientasGradleApplication {

    public static void main(String[] args) {
        SpringApplication.run(
            HerramientasGradleApplication.class,
            args
        );
    }
}
