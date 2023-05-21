package com.example.partnersapi.domain.partner;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Partner(PartnerRequestDTO data){
        this.tradingname = data.tradingName();
        this.ownername = data.ownerName();
        this.document = data.document();
        this.coordinatex = data.address().coordinates().get(0);
        this.coordinatey = data.address().coordinates().get(1);
    }
}
