package com.github.superai.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class BenefitsController {

    @GetMapping("/benefits")
    public String viewBenefits() {
        return "benefits";
    }

}
