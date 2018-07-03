package com.hcl.tfg.notification.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Component
public class HttpService {

    public <T, R> T getApiResponse(R requestBody, MultiValueMap<String, String> headers, String url, HttpMethod type,
            Class<T> clazz) {
        HttpEntity<?> request = new HttpEntity<R>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> response = null;
        try {
            response = restTemplate.exchange(url, type, request, clazz);
        } catch (HttpStatusCodeException e) {
            String errorpayload = e.getResponseBodyAsString();
            throw new RuntimeException(errorpayload);
        } catch (RestClientException e) {
            throw new RuntimeException(e.getMessage());
        }
        return response.getBody();
    }

    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
        return headers;
    }

}
