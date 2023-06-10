package com.example.partnersapi.controllers;

import com.example.partnersapi.domain.address.AddressDTO;
import com.example.partnersapi.domain.address.AddressService;
import com.example.partnersapi.domain.area.CoverageArea;
import com.example.partnersapi.domain.partner.Partner;
import com.example.partnersapi.domain.partner.PartnerRequestDTO;
import com.example.partnersapi.domain.partner.PartnerResponseDTO;
import com.example.partnersapi.infra.exceptions.BadRequestException;
import com.example.partnersapi.repositories.PartnerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
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
    public ResponseEntity postPartner(@RequestBody @Valid PartnerRequestDTO data, UriComponentsBuilder uriBuilder) throws BadRequestException {
        String completeAddress = addressService.getCompleteAddress(uriBuilder, data.address());
        String city = addressService.getCityFromCompleteAddress(completeAddress);
        String country = addressService.getCountryFromCompleteAddress(completeAddress);

        Partner newPartner = new Partner(data, city, country);
        this.repository.save(newPartner);
        PartnerResponseDTO response = new PartnerResponseDTO(newPartner);

        var uri = uriBuilder.path("/partner/{id}").buildAndExpand(newPartner.getId()).toUri();
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

    @GetMapping("/search")
    public ResponseEntity searchByLocation(@RequestBody @Valid AddressDTO address, UriComponentsBuilder uriBuilder) throws BadRequestException {
        if(!address.type().trim().equalsIgnoreCase("point")) throw new BadRequestException("Wrong argument Address provided, type " + address.type() + " not supported");

        String completeAddress = addressService.getCompleteAddress(uriBuilder, address);
        String city = addressService.getCityFromCompleteAddress(completeAddress);
        String country = addressService.getCountryFromCompleteAddress(completeAddress);

        List<PartnerResponseDTO> foundedPartners = this.repository.findAllByCountryAndCity(country, city)
                .stream()
                .filter(element -> new CoverageArea(element.getCoverageArea()).isCoordinateInside(address.coordinates().get(0), address.coordinates().get(1)))
                .map(PartnerResponseDTO::new)
                .toList();

        return ResponseEntity.ok(foundedPartners);
    }
}
