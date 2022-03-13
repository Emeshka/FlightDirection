package ru.emeshka.springreacttest.flightdirection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.emeshka.springreacttest.flightdirection.message.ResponseArticle;
import ru.emeshka.springreacttest.flightdirection.model.Article;
import ru.emeshka.springreacttest.flightdirection.service.DestinationService;

@Controller
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @PutMapping("/api/destination/article/{lang}/{id}")
    public ResponseEntity<ResponseArticle> addArticle(
            @PathVariable String lang,
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String text
    ) {
        String message;
        try {
            Article newArticle = destinationService.addArticle(id, lang, title, text);
            return ResponseEntity.ok().body(new ResponseArticle(newArticle, ""));
        } catch (Exception e) {
            message = "Could not save a new article for country with id: " + id + " and lang: " + lang + "!";
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseArticle(null, message));
        }
    }
}
