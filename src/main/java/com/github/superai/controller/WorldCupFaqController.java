package com.github.superai.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class WorldCupFaqController {

    @GetMapping("/worldcup")
    public String viewWc() {
        return "wc";
    }

}
