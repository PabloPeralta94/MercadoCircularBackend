package com.mercado.circular.controller;

import javax.annotation.PostConstruct; // java 11 = javax, java 17 = jakarta
import javax.servlet.http.HttpServletRequest; // java 11 = javax, java 17 = jakarta
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @PostConstruct
    public void metodoPreIniciacion() {

        System.out.println("--------------------");
        System.out.println("POSTCONSTRUCT METHOD INITIALIZED");
        System.out.println("--------------------");

    }

    @GetMapping("/getPruebaBasica")
    public String getStringBasico(HttpServletRequest req) {

        System.out.println("Probando probando, probando mi amor por ti!");

        return "cools";
    }
}