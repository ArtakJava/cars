package org.example.client;

import org.example.dto.car.CarDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class CarClient extends BaseClient {
    private static final String API_PREFIX = "/car";

    public CarClient(@Value("${cars-server.url}") String serverURL, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverURL + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> createCar(CarDto carDto) {
        return post("", carDto);
    }

    public ResponseEntity<Object> getCars() {
        return get("");
    }

    public ResponseEntity<Object> getCar(long carId) {
        return get("/" + carId);
    }
}