package ru.emeshka.springreacttest.flightdirection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.emeshka.springreacttest.flightdirection.model.FileBlob;
import ru.emeshka.springreacttest.flightdirection.repository.FileBlobRepository;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileBlobService {
    @Autowired
    private FileBlobRepository fileBlobRepository;

    public FileBlob store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileBlob fileBlob = new FileBlob(fileName, file.getContentType(), file.getBytes());
        return fileBlobRepository.save(fileBlob);
    }
    public FileBlob get(String id) {
        return fileBlobRepository.findById(id).get();
    }

    public Stream<FileBlob> getAll() {
        return fileBlobRepository.findAll().stream();
    }
}