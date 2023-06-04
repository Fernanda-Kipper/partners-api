package com.example.partnersapi.domain.address;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;

public interface AddressService {
         public String getCompleteAddress(UriComponentsBuilder uriBuilder, AddressDTO address) throws URISyntaxException;
}