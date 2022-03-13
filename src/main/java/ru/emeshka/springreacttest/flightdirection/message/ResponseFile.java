package ru.emeshka.springreacttest.flightdirection.message;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.emeshka.springreacttest.flightdirection.model.FileBlob;

public class ResponseFile {
    private final String NO_IMAGE = "/img/noimg.webp";
    private final String NO_IMAGE_TYPE = "image/webp";

    private String name;
    private String url;
    private String type;
    private long size;

    private void fillEmpty() {
        this.name = "no image";
        this.url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(NO_IMAGE)
                .toUriString();
        this.type = NO_IMAGE_TYPE;
        this.size = -1;
    }

    public ResponseFile(FileBlob fileBlobModel) {
        if (fileBlobModel == null) {
            fillEmpty();
        } else {
            this.name = fileBlobModel.getName();
            this.url = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/file/")
                    .path(fileBlobModel.getId())
                    .toUriString();
            this.type = fileBlobModel.getType();
            this.size = fileBlobModel.getData().length;
        }
    }

    public ResponseFile() {
        fillEmpty();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
