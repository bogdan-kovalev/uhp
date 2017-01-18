package com.uhp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Bogdan Kovalev
 *         Created on 1/17/17.
 */
@Controller
public class IndexController {
    @GetMapping(value = "/")
    public String redirectToUi() {
        return "redirect:/ui/";
    }
}
