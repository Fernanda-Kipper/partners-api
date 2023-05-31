package com.example.partnersapi.domain.partner;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.MultiPolygon;

@Table(name = "partner")
@Entity(name = "partner")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Partner {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String tradingname;
    private String ownername;
    private String document;
    private Float coordinatex;
    private Float coordinatey;

    @Column(name = "coveragearea", columnDefinition = "geometry")

    private MultiPolygon coverageArea;

    public Partner(PartnerRequestDTO data, MultiPolygon coverageArea){
        this.tradingname = data.tradingName();
        this.ownername = data.ownerName();
        this.document = data.document();
        this.coordinatex = data.address().coordinates().get(0);
        this.coordinatey = data.address().coordinates().get(1);
        this.coverageArea = coverageArea;
    }
}
