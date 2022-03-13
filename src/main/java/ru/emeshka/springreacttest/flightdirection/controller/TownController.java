package ru.emeshka.springreacttest.flightdirection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.emeshka.springreacttest.flightdirection.message.ResponseArticle;
import ru.emeshka.springreacttest.flightdirection.message.ResponseNewDestination;
import ru.emeshka.springreacttest.flightdirection.message.ResponseTownPreview;
import ru.emeshka.springreacttest.flightdirection.model.Article;
import ru.emeshka.springreacttest.flightdirection.model.Town;
import ru.emeshka.springreacttest.flightdirection.service.ArticleService;
import ru.emeshka.springreacttest.flightdirection.service.TownService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TownController {
    @Autowired
    private TownService townService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/towns/{lang}")
    public ResponseEntity<List<ResponseTownPreview>> getTowns(
            @PathVariable String lang
    ) {
        List<ResponseTownPreview> list = townService.getAll()
                .map(town -> new ResponseTownPreview(town, lang, articleService))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/api/town")
    public ResponseEntity<ResponseNewDestination> createTown(
            @RequestParam("nameOriginal") String nameOriginal
    ) {
        String message;
        try {
            Town town = townService.store(nameOriginal);
            return ResponseEntity.ok().body(new ResponseNewDestination(town, ""));
        } catch (IOException e) {
            message = "Could not save a new town with name: " + nameOriginal + "!";
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseNewDestination(null, message));
        }
    }
}
