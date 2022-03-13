package ru.emeshka.springreacttest.flightdirection.model;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town extends Destination {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    public Country getCountry() {
        return country;
    }
}
