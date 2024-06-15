package com.github.superai.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class JokeController {

    @GetMapping("/jokes")
    public String viewJokes() {
        return "jokes";
    }

}
