package ru.emeshka.springreacttest.flightdirection.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameTransliterated;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "destination")
    private List<Article> articles;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "destination")
    private List<FileBlob> images;

    public long getId() {
        return id;
    }

    public String getNameTransliterated() {
        return nameTransliterated;
    }

    public void setNameTransliterated(String nameTransliterated) {
        this.nameTransliterated = nameTransliterated;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<FileBlob> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return getNameTransliterated();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return getId() == country.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameTransliterated(), getId());
    }
}
