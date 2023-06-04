package com.example.partnersapi.infra.googlemaps;

import com.example.partnersapi.domain.address.AddressDTO;
import com.example.partnersapi.domain.address.AddressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GoogleMapsService implements AddressService {

    @Value("${api.googlemaps.token}")
    private String googleToken;

    @Value("${api.googlemaps.uri}")
    private String mapsUri;
    public String getCompleteAddress(UriComponentsBuilder uriBuilder, AddressDTO address) throws URISyntaxException {
        System.out.println("chegou aqui");
        System.out.println(this.mapsUri + this.googleToken);
        String latlng = address.coordinates().get(0).toString() + "," + address.coordinates().get(1).toString();
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

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
        return response.body();
    }
}
