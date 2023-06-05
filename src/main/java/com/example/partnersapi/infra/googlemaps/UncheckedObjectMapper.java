package com.example.partnersapi.infra.googlemaps;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;

public class UncheckedObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {
    GoogleApiResponse readValue(String content) {
        try {
            return this.readValue(content, new TypeReference<GoogleApiResponse>() {
            });
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}

