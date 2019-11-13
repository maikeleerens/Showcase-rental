package com.rental.Api.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

    @RequestMapping("/")
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }
}
