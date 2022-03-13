package ru.emeshka.springreacttest.flightdirection.service;

import com.ibm.icu.text.Transliterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.emeshka.springreacttest.flightdirection.model.Article;
import ru.emeshka.springreacttest.flightdirection.model.Destination;
import ru.emeshka.springreacttest.flightdirection.model.Town;
import ru.emeshka.springreacttest.flightdirection.repository.DestinationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DestinationService {
    private final String TRANSLITERATION_SETTINGS = "Any-Latin; Lower; Latin-ASCII";
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private ArticleService articleService;

    protected void setCommonProperties(Destination destination, String nameOriginal) {
        String nameTransliterated = Transliterator.getInstance(TRANSLITERATION_SETTINGS).transform(nameOriginal);
        destination.setNameTransliterated(nameTransliterated);
    }

    public Destination get(Long id) throws Exception {
        Optional<Destination> destination = destinationRepository.findById(id);
        if (destination.isEmpty()) throw new Exception("Destination with id " + id + " not found");
        return destination.get();
    }

    public Stream<Destination> getAll() {
        return destinationRepository.findAll().stream();
    }

    public List<String> getAvailableLanguages(Long destinationId) throws Exception {
        return articleService.getAvailableLanguagesForDestinationId(destinationId);
    }

    public Article addArticle(Long destinationId, String language, String title, String text) throws Exception {
        Destination destination = this.get(destinationId);
        if (destination == null) {
            throw new Exception("Cannot create an article for no destination (null)");
        }
        // should save Destination<->>Article both sides?
        Article newArticle = articleService.createOrUpdate(destination, language, title, text);
        if (newArticle == null) {
            throw new Exception("Cannot add no article (null) to a destination: destinationId: "
                    +destination.getId()
                    +", article params: "+language
                    +", title: "+title
                    +", text: "+text
            );
        }
        return newArticle;
    }
}
