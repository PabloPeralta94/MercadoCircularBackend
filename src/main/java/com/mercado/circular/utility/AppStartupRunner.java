package com.mercado.circular.utility;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("===================== BACKEND RUNNING =====================");
    }
}