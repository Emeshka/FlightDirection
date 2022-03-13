package ru.emeshka.springreacttest.flightdirection.message;

import ru.emeshka.springreacttest.flightdirection.model.Destination;

public class ResponseNewDestination {
    public String nameTransliterated;
    public Long id;
    public String message;

    public ResponseNewDestination(Destination destination, String message) {
        if (destination == null) {
            this.id = null;
            this.nameTransliterated = null;
        } else {
            this.id = destination.getId();
            this.nameTransliterated = destination.getNameTransliterated();
        }
        this.message = message;
    }
}
