package ru.emeshka.springreacttest.flightdirection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.emeshka.springreacttest.flightdirection.message.ResponseArticle;
import ru.emeshka.springreacttest.flightdirection.message.ResponseCountryPreview;
import ru.emeshka.springreacttest.flightdirection.message.ResponseNewDestination;
import ru.emeshka.springreacttest.flightdirection.model.Article;
import ru.emeshka.springreacttest.flightdirection.model.Country;
import ru.emeshka.springreacttest.flightdirection.service.ArticleService;
import ru.emeshka.springreacttest.flightdirection.service.CountryService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:8081")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/countries/{lang}")
    public ResponseEntity<List<ResponseCountryPreview>> getCountries(
            @PathVariable String lang
    ) {
        List<ResponseCountryPreview> countries = countryService.getAll()
                .map(country -> new ResponseCountryPreview(country, lang, articleService))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(countries);
    }

    @PostMapping("/api/country")
    public ResponseEntity<ResponseNewDestination> createCountry(
            @RequestParam("nameOriginal") String nameOriginal
    ) {
        String message;
        try {
            Country newCountry = countryService.store(nameOriginal);
            return ResponseEntity.ok().body(new ResponseNewDestination(newCountry, ""));
        } catch (Exception e) {
            message = "Could not save a new country with name: " + nameOriginal + "!";
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseNewDestination(null, message));
        }
    }
}
