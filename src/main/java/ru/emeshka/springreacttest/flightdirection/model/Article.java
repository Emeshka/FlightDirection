package ru.emeshka.springreacttest.flightdirection.model;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String text;
    private String language;
    // Article can be both about country and city. That's why I'm using 'joined' inheritance strategy here
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Article() {}

    public Article(String lang, Destination destination, String title, String text) {
        setLanguage(lang);
        setDestination(destination);
        setText(text);
        setTitle(title);
    }
}
