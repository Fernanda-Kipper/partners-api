package com.example.partnersapi.domain.address;

import com.example.partnersapi.infra.exceptions.BadRequestException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;

public interface AddressService {
         public String getCompleteAddress(UriComponentsBuilder uriBuilder, AddressDTO address) throws RuntimeException;

         public String getCityFromCompleteAddress(String completeAddress) throws BadRequestException;

         public String getCountryFromCompleteAddress(String completeAddress);
}
