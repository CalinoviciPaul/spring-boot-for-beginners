package com.in28minutes.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by IrianLaptop on 7/6/2017.
 */


@Component
public class WelcomeService {


    @Value("${welcome.message}")
    private String welcomeMessage;

    public String retrieveWelcomeMessage() {
        //Complex Method
        return this.welcomeMessage;
    }
}

