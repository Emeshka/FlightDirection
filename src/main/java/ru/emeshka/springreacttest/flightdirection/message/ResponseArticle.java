package ru.emeshka.springreacttest.flightdirection.message;

import ru.emeshka.springreacttest.flightdirection.model.Article;
import ru.emeshka.springreacttest.flightdirection.model.Destination;

public class ResponseArticle {
    private Long id;
    public String languageCode;
    public Long destinationId;
    public String title;
    public String text;
    public String message;

    public ResponseArticle(Article article, String message) {
        if (article == null) {
            this.id = null;
            this.languageCode = null;
            this.destinationId = null;
            this.title = null;
            this.text = null;
        } else {
            this.id = article.getId();
            this.languageCode = article.getLanguage();
            this.title = article.getTitle();
            this.text = article.getText();
            Destination dest = article.getDestination();
            if (dest == null) {
                this.destinationId = null;
            } else {
                this.destinationId = dest.getId();
            }
        }
        this.message = message;
    }
}
