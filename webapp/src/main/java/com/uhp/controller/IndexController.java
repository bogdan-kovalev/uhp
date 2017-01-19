package com.uhp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Bogdan Kovalev.
 */
@Controller
public class IndexController {
    @GetMapping(value = "/")
    public String redirectToUi() {
        return "redirect:/ui/";
    }
}
