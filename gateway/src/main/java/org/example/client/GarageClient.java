package org.example.client;

import org.example.dto.garage.GarageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class GarageClient extends BaseClient {
    private static final String API_PREFIX = "/garage";

    public GarageClient(@Value("${cars-server.url}") String serverURL, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverURL + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> createGarage(GarageDto garageDto) {
        return post("", garageDto);
    }

    public ResponseEntity<Object> getGarages() {
        return get("");
    }

    public ResponseEntity<Object> getGarage(long garageId) {
        return get("/" + garageId);
    }
}