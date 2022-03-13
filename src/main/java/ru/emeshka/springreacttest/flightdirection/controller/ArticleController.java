package ru.emeshka.springreacttest.flightdirection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.emeshka.springreacttest.flightdirection.message.ResponseArticle;
import ru.emeshka.springreacttest.flightdirection.model.Article;
import ru.emeshka.springreacttest.flightdirection.service.ArticleService;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //creation of article is provided in Country and Town API
    @GetMapping("/api/article/{lang}/{destination_id}")
    public ResponseEntity<ResponseArticle> getArticle(
            @PathVariable String lang,
            @PathVariable Long destination_id
    ) {
        Article article = articleService.get(destination_id, lang);
        return ResponseEntity.ok().body(new ResponseArticle(article, ""));
    }
}
