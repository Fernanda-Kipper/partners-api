package com.example.partnersapi.infra.googlemaps;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GoogleApiResponse(@JsonProperty("plus_code") PlusCode plusCode, List<Result> results, String status) {
    public record PlusCode(@JsonProperty("compound_code") String compoundCode, @JsonProperty("global_code") String globalCode) {}

    public record Result(@JsonProperty("address_components") List<AddressComponent> addressComponents, @JsonProperty("formatted_address") String formattedAddress, Geometry geometry,
                         @JsonProperty("place_id") String placeId, @JsonProperty("plus_code") PlusCode plusCode, List<String> types) {
        public record AddressComponent(@JsonProperty("long_name") String longName, @JsonProperty("short_name") String shortName, List<String> types) {}

        public record Geometry(Viewport bounds, Location location, @JsonProperty("location_type") String locationType, Viewport viewport) {
            public record Location(double lat, double lng) {}

            public record Viewport(Location northeast, Location southwest) {}
        }
    }
}

