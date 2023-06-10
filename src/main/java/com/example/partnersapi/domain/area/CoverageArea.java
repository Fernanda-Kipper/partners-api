package com.example.partnersapi.domain.area;

import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;

@AllArgsConstructor
public class CoverageArea {

    MultiPolygon coverageArea;

    public boolean isCoordinateInside(double x, double y) {
        Geometry point = createPoint(x, y);
        return this.coverageArea.contains(point);
    }

    private Geometry createPoint(double x, double y) {
        GeometryFactory geometryFactory = new GeometryFactory();
        return geometryFactory.createPoint(new Coordinate(x,y));
    }
}
