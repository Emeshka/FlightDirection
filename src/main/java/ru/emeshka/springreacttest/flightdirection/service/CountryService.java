package ru.emeshka.springreacttest.flightdirection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.emeshka.springreacttest.flightdirection.model.Article;
import ru.emeshka.springreacttest.flightdirection.model.Country;
import ru.emeshka.springreacttest.flightdirection.repository.CountryRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private DestinationService destinationService;

    public Country store(String nameOriginal) throws IOException {
        Country country = new Country();
        destinationService.setCommonProperties(country, nameOriginal);
        return countryRepository.save(country);
    }

    public Country get(Long id) throws Exception {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isEmpty()) {
            throw new Exception("Country with id " + id + " not found");
        }
        return country.get();
    }

    public Stream<Country> getAll() {
        return countryRepository.findAll().stream();
    }

    /*public Article addArticle(Long id, String language, String title, String text) throws Exception {
        Article newArticle = destinationService.addArticle(this.get(id), language, title, text);
        return newArticle;
    }*/
}
