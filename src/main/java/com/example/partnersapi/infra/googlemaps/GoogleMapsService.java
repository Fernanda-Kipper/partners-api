package com.example.partnersapi.infra.googlemaps;

import com.example.partnersapi.domain.address.AddressDTO;
import com.example.partnersapi.domain.address.AddressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

@Service
public class GoogleMapsService implements AddressService {

    @Value("${api.googlemaps.token}")
    private String googleToken;

    @Value("${api.googlemaps.uri}")
    private String mapsUri;
    public String getCompleteAddress(UriComponentsBuilder uriBuilder, AddressDTO address) {
        UncheckedObjectMapper uncheckedObjectMapper = new UncheckedObjectMapper();
        String latlng = address.coordinates().get(1).toString() + "," + address.coordinates().get(0).toString();
        URI uri = uriBuilder.fromUriString(this.mapsUri)
                .queryParam("latlng", latlng)
                .queryParam("sensor", true)
                .queryParam("key", this.googleToken)
                .build()
                .toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        GoogleApiResponse response = null;
        try {
            response = HttpClient.newHttpClient()
                    .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(uncheckedObjectMapper::readValue)
                    .get();
            System.out.println(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response.results().get(0).formattedAddress();
    }

    public String getCityFromCompleteAddress(String completeAddress) {
        var addressParts = completeAddress.split(",", 5);
        var cityAndState = addressParts[2].split("-");
        var city = cityAndState[0];
        return city;
    }

    public String getCountryFromCompleteAddress(String completeAddress) {
        var addressParts = completeAddress.split(",", 5);
        var country = addressParts[addressParts.length - 1];
        return country;
    }
}
