package ru.emeshka.springreacttest.flightdirection.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country extends Destination {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flag_id")
    private FileBlob flag;
    private String money;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capital_id")
    private Town capital;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<Town> towns;

    public List<Town> getTowns() {
        return towns;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Town getCapital() {
        return capital;
    }

    public void setCapital(Town capital) {
        this.capital = capital;
    }

    public FileBlob getFlag() {
        return flag;
    }

    public void setFlag(FileBlob flag) {
        this.flag = flag;
    }
}
