package com.example.partnersapi.infra.googlemaps;

import com.example.partnersapi.domain.address.AddressService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfiguration {
    @Bean
    public AddressService addressService(){
        return new GoogleMapsService();
    }
}
