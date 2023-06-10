package com.example.partnersapi.domain.partner;

import com.example.partnersapi.domain.area.CoverageAreaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.*;

import java.util.ArrayList;

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
    private String city;
    private String country;

    @Column(name = "coveragearea", columnDefinition = "geometry")

    private MultiPolygon coverageArea;

    public Partner(PartnerRequestDTO data, String city, String country){
        this.tradingname = data.tradingName();
        this.ownername = data.ownerName();
        this.document = data.document();
        this.coordinatex = data.address().coordinates().get(0);
        this.coordinatey = data.address().coordinates().get(1);
        this.coverageArea = formatCoverageArea(data.coverageArea());
        this.city = city;
        this.country = country;
    }

    private MultiPolygon formatCoverageArea(CoverageAreaDTO coverageData){
        ArrayList<Polygon> multiPolygons = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();

        coverageData.coordinates().forEach(polygons -> polygons.forEach(points -> {
            Coordinate[] polygonCoords = new Coordinate[points.size()];
            for (int i = 0; i < points.size(); i++) {
                ArrayList<Float> coord = points.get(i);
                polygonCoords[i] = new Coordinate(coord.get(0), coord.get(1));
            }
            LinearRing shell = geometryFactory.createLinearRing(polygonCoords);
            multiPolygons.add(geometryFactory.createPolygon(shell));
        }));

        MultiPolygon coverageArea = geometryFactory.createMultiPolygon(multiPolygons.toArray(new Polygon[0]));

        return coverageArea;
    }
}
