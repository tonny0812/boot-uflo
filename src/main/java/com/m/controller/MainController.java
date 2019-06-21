package com.m.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    protected Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/login")
    public String toLogin() {
        return "/login";
    }
    @GetMapping("/operation")
    public String toOperation() {
        logger.info("begin user operation...");
        return "/operation";
    }
}
