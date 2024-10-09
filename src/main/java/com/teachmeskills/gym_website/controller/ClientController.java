package com.teachmeskills.gym_website.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {

    @GetMapping(path = "/selection-pass")
    private String selectionPass(Model model) {
        return "seasonTicket";
    }

    @GetMapping(path = "/checkout")
    private String checkout(Model model) {
        return "checkout";
    }

}

