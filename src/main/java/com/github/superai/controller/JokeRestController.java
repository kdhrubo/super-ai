package com.github.superai.controller;

import com.github.superai.dto.Joke;
import com.github.superai.service.JokeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class JokeRestController {

    private final JokeService jokeService;

    @GetMapping("/jokes")
    public Joke viewJokes() {
        return new Joke(UUID.randomUUID().toString(), jokeService.getJoke());

    }

}
