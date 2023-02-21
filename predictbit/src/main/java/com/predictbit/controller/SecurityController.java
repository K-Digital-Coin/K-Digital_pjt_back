package com.predictbit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    private static final Logger log = LoggerFactory.getLogger(SecurityController.class);
    @GetMapping("/")
    public String index() {
        log.info("Request for index");
        return "index";
    }

    @GetMapping("/member")
    public void forMember() {
        log.info("Request for Member");
    }

    @GetMapping("/manager")
    public void forManager() {
        log.info("Request for Manager");
    }

    @GetMapping("/admin")
    public void forAdmin() {
        log.info("Request for Admin");
    }

    @GetMapping("/accessDenied")
    public void accessDenied() {

    }
}
