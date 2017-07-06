package com.in28minutes.springboot.service;

import org.springframework.stereotype.Component;

/**
 * Created by IrianLaptop on 7/6/2017.
 */


@Component
public class WelcomeService {

    public String retrieveWelcomeMessage() {
        //Complex Method
        return "Good Morning updated";
    }
}

