package ru.emeshka.springreacttest.flightdirection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.emeshka.springreacttest.flightdirection.model.Article;
import ru.emeshka.springreacttest.flightdirection.model.Destination;
import ru.emeshka.springreacttest.flightdirection.repository.ArticleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article get(Long destinationId, String language) {
        return articleRepository.findByLanguageAndDestination_Id(language, destinationId);
    }

    public List<String> getAvailableLanguagesForDestinationId(Long destinationId) {
        return articleRepository
                .findAllProjectedByDestinationId(destinationId)
                .stream().map(ArticleRepository.ProjectionLanguage::getLanguage)
                .collect(Collectors.toList());
    }

    public Article createOrUpdate(Destination dest, String language, String title, String text) throws Exception {
        if (dest == null) {
            throw new Exception("Cannot create an article for no destination (null)");
        }
        Article article = this.get(dest.getId(), language);
        if (article == null) {
            article = new Article(language, dest, title, text);
        } else {
            article.setTitle(title);
            article.setText(text);
        }
        // should save Destination<->>Article both sides?
        return articleRepository.save(article);
    }
}
