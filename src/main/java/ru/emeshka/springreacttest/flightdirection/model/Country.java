package ru.emeshka.springreacttest.flightdirection.model;

import java.util.Objects;

public class Country {
    private String name;
    private String flag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return name.equals(country.name) && flag.equals(country.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, flag);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
