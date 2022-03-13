package ru.emeshka.springreacttest.flightdirection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.emeshka.springreacttest.flightdirection.model.Article;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    public interface ProjectionLanguage {
        String getLanguage();
    }

    public Article findByLanguageAndDestination_Id(String language, Long destinationId);
    public List<ProjectionLanguage> findAllProjectedByDestinationId(Long destinationId);
}
