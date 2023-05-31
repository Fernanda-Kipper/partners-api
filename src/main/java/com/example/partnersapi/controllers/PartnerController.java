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
        ArrayList<Polygon> multiPolygons = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();

        data.coverageArea().coordinates().forEach(polygons -> polygons.forEach(points -> {
            Coordinate[] polygonCoords = new Coordinate[points.size()];
            for (int i = 0; i < points.size(); i++) {
                ArrayList<Float> coord = points.get(i);
                polygonCoords[i] = new Coordinate(coord.get(0), coord.get(1));
            }
            LinearRing shell = geometryFactory.createLinearRing(polygonCoords);
            multiPolygons.add(geometryFactory.createPolygon(shell));
        }));

        MultiPolygon coverageArea = geometryFactory.createMultiPolygon(multiPolygons.toArray(new Polygon[0]));

        Partner newPartner = new Partner(data, coverageArea);

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
