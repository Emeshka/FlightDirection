package ru.emeshka.springreacttest.flightdirection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

import ru.emeshka.springreacttest.flightdirection.message.ResponseFile;
import ru.emeshka.springreacttest.flightdirection.message.ResponseMessage;
import ru.emeshka.springreacttest.flightdirection.model.FileBlob;
import ru.emeshka.springreacttest.flightdirection.service.FileBlobService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:8081")
public class FileController {
    @Autowired
    private FileBlobService storageService;

    @PostMapping("/api/upload")
    public ResponseEntity<ResponseMessage> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        String message = "";
        try {
            storageService.store(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/api/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAll().map(ResponseFile::new).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/api/file/{id}")
    public ResponseEntity<String> getFile(@PathVariable String id) {
        FileBlob fileBlob = storageService.get(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileBlob.getName() + "\"")
                .body(Base64.getEncoder().encodeToString(fileBlob.getData()));
    }
}
