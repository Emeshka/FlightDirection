package ru.emeshka.springreacttest.flightdirection.message;

import ru.emeshka.springreacttest.flightdirection.model.Article;
import ru.emeshka.springreacttest.flightdirection.model.Country;
import ru.emeshka.springreacttest.flightdirection.model.FileBlob;
import ru.emeshka.springreacttest.flightdirection.model.Town;
import ru.emeshka.springreacttest.flightdirection.service.ArticleService;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseCountryPreview {
    private final int TEXT_SNIPPET_LENGTH = 300;

    public Long id;
    public String languageCode;
    public List<String> availableLanguages;
    public String nameTransliterated;
    public String title;
    public String textSnippet;
    public List<ResponseTownPreview> cities;
    public ResponseFile sampleImage;
    public ResponseTownPreview capital;
    public String money;
    public ResponseFile flag;

    public ResponseCountryPreview(Country country, String language, ArticleService articleService) {
        this.languageCode = language;
        if (country != null) {
            this.id = country.getId();
            this.nameTransliterated = country.getNameTransliterated();
            List<Town> cities = country.getTowns();
            if (cities == null) {
                this.cities = null;
            } else {
                this.cities = cities.stream()
                        .map(city -> new ResponseTownPreview(city, languageCode, articleService))
                        .collect(Collectors.toList());
            }
            this.capital = new ResponseTownPreview(country.getCapital(), languageCode, articleService);
            this.money = country.getMoney();
            this.flag = new ResponseFile(country.getFlag());
            List<FileBlob> images = country.getImages();
            if (images == null || images.size() == 0) {
                this.sampleImage = new ResponseFile();
            } else {
                this.sampleImage = new ResponseFile(images.get(0));
            }

            Article articleAboutCountry = articleService.get(id, language);
            if (articleAboutCountry == null) {
                this.title = this.nameTransliterated;
                this.textSnippet = "";
            } else {
                this.title = articleAboutCountry.getTitle();
                String text = articleAboutCountry.getText();
                this.textSnippet = text.substring(0, Math.min(TEXT_SNIPPET_LENGTH, text.length()));
            }

            this.availableLanguages = articleService.getAvailableLanguagesForDestinationId(this.id);
        }
    }
}
