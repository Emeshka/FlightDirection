package ru.emeshka.springreacttest.flightdirection.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.emeshka.springreacttest.flightdirection.model.FileBlob;

@Repository
public interface FileBlobRepository extends JpaRepository<FileBlob, String> {
}
