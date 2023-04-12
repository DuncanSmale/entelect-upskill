package com.entelect.services.common;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class RestTemplateCaller<Request, Response> {

    private final RestTemplate restTemplate;

    public ResponseEntity<Response> postForEntity(URI uri,
                                                  Request entity,
                                                  Class<Response> type) {
        return restTemplate.postForEntity(
                uri,
                entity,
                type
        );
    }
}