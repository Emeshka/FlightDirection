package ru.emeshka.springreacttest.flightdirection.message;

import ru.emeshka.springreacttest.flightdirection.model.Article;
import ru.emeshka.springreacttest.flightdirection.model.Country;
import ru.emeshka.springreacttest.flightdirection.model.FileBlob;
import ru.emeshka.springreacttest.flightdirection.model.Town;
import ru.emeshka.springreacttest.flightdirection.service.ArticleService;

import java.util.List;

public class ResponseTownPreview {
    private final int TEXT_SNIPPET_LENGTH = 300;

    public Long id;
    public String languageCode;
    public String nameTransliterated;
    public String title;
    public String textSnippet;
    public Long countryId;
    public String countryNameTransliterated;
    public String countryTitle;
    public ResponseFile sampleImage;

    public ResponseTownPreview(Town town, String languageCode, ArticleService articleService) {
        if (town != null) {
            this.id = town.getId();
            this.nameTransliterated = town.getNameTransliterated();
            this.languageCode = languageCode;
            List<FileBlob> images = town.getImages();
            if (images == null || images.size() == 0) {
                this.sampleImage = new ResponseFile();
            } else {
                this.sampleImage = new ResponseFile(images.get(0));
            }

            Country country = town.getCountry();
            this.countryId = country.getId();
            this.countryNameTransliterated = country.getNameTransliterated();

            Article countryArticle = articleService.get(this.countryId, languageCode);
            if (countryArticle == null) {
                this.countryTitle = this.countryNameTransliterated;
            } else {
                this.countryTitle = countryArticle.getTitle();
            }

            Article townArticle = articleService.get(this.id, languageCode);
            if (townArticle == null) {
                this.title = this.nameTransliterated;
                this.textSnippet = "";
            } else {
                this.title = townArticle.getTitle();
                String text = townArticle.getText();
                this.textSnippet = text.substring(0, Math.min(TEXT_SNIPPET_LENGTH, text.length()));
            }
        }
    }
}
