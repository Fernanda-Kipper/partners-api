package com.example.partnersapi.controllers;

import com.example.partnersapi.domain.partner.Partner;
import com.example.partnersapi.domain.partner.PartnerRequestDTO;
import com.example.partnersapi.repositories.PartnerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.locationtech.jts.geom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("partner")
public class PartnerController {

    @Autowired
    PartnerRepository repository;
    @PostMapping
    public ResponseEntity postPartner(@RequestBody @Valid PartnerRequestDTO data, UriComponentsBuilder UriBuilder){
        Partner newPartner = new Partner(data);
        this.repository.save(newPartner);
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
