package ru.emeshka.springreacttest.flightdirection.message;

import java.util.List;

public class ResponseDestinationPreview {
    public Long id;
    public String languageCode;
    public String title;
    public String textSnippet;
    public List<ResponseDestinationPreview> cities;
    public ResponseFile sampleImage;
}
