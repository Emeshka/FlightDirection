package ru.emeshka.springreacttest.flightdirection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.emeshka.springreacttest.flightdirection.model.Destination;

@Repository
public interface DestinationService extends JpaRepository<Destination, Long> {
}
