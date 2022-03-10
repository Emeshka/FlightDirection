package ru.emeshka.springreacttest.flightdirection.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.emeshka.springreacttest.flightdirection.model.FileBlob;

import java.util.UUID;

@Repository
public interface FileBlobRepository extends JpaRepository<FileBlob, String> {
}
