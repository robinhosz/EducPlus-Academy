package br.com.unifg.educplus.application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class UserController {


    @PostMapping
    public String hello() {
        return "Hello Student";
    }
}