package ru.emeshka.springreacttest.flightdirection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.emeshka.springreacttest.flightdirection.model.Article;
import ru.emeshka.springreacttest.flightdirection.model.Town;
import ru.emeshka.springreacttest.flightdirection.repository.TownRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TownService {
    @Autowired
    private TownRepository townRepository;
    @Autowired
    private DestinationService destinationService;

    public Town store(String nameOriginal) throws IOException {
        Town town = new Town();
        destinationService.setCommonProperties(town, nameOriginal);
        return townRepository.save(town);
    }

    public Town get(Long id) throws Exception {
        Optional<Town> town = townRepository.findById(id);
        if (town.isEmpty()) throw new Exception("Town with id " + id + " not found");
        return town.get();
    }

    public Stream<Town> getAll() {
        return townRepository.findAll().stream();
    }

    /*public Article addArticle(Long id, String language, String title, String text) throws Exception {
        Article newArticle = destinationService.addArticle(this.get(id), language, title, text);
        return newArticle;
    }*/
}
