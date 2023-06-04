package com.example.partnersapi.controllers;

import com.example.partnersapi.domain.address.AddressService;
import com.example.partnersapi.domain.partner.Partner;
import com.example.partnersapi.domain.partner.PartnerRequestDTO;
import com.example.partnersapi.repositories.PartnerRepository;

import com.example.partnersapi.infra.googlemaps.GoogleMapsService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("partner")
public class PartnerController {

    @Autowired
    PartnerRepository repository;

    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity postPartner(@RequestBody @Valid PartnerRequestDTO data, UriComponentsBuilder UriBuilder){
        Partner newPartner = new Partner(data);
        this.repository.save(newPartner);

        try {
            addressService.getCompleteAddress(UriBuilder, data.address());
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        var uri = UriBuilder.path("/partner/{id}").buildAndExpand(newPartner.getId()).toUri();

        return ResponseEntity.created(uri).body(newPartner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partner> getById(@PathVariable String id){
        Optional<Partner> foundedPartner = this.repository.findById(id);
        if(foundedPartner.isEmpty()) throw new EntityNotFoundException();

        Partner partner = foundedPartner.get();

        return ResponseEntity.ok().body(partner);
    }
}
