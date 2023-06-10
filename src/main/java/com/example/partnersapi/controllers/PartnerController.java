package com.example.partnersapi.controllers;

import com.example.partnersapi.domain.address.AddressService;
import com.example.partnersapi.domain.partner.Partner;
import com.example.partnersapi.domain.partner.PartnerRequestDTO;
import com.example.partnersapi.domain.partner.PartnerResponseDTO;
import com.example.partnersapi.repositories.PartnerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("partner")
public class PartnerController {

    @Autowired
    PartnerRepository repository;

    @Autowired
    AddressService addressService;

    @PostMapping
    @Transactional
    public ResponseEntity postPartner(@RequestBody @Valid PartnerRequestDTO data, UriComponentsBuilder UriBuilder) {
        String completeAddress = addressService.getCompleteAddress(UriBuilder, data.address());
        String city = addressService.getCityFromCompleteAddress(completeAddress);
        String country = addressService.getCountryFromCompleteAddress(completeAddress);

        Partner newPartner = new Partner(data, city, country);
        this.repository.save(newPartner);
        PartnerResponseDTO response = new PartnerResponseDTO(newPartner);

        var uri = UriBuilder.path("/partner/{id}").buildAndExpand(newPartner.getId()).toUri();
        return ResponseEntity.created(uri).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> getById(@PathVariable String id){
        Optional<Partner> foundedPartner = this.repository.findById(id);
        if(foundedPartner.isEmpty()) throw new EntityNotFoundException();

        Partner partner = foundedPartner.get();
        PartnerResponseDTO response = new PartnerResponseDTO(partner);

        return ResponseEntity.ok().body(response);
    }
}
