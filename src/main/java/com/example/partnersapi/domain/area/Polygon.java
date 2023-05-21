package com.example.partnersapi.domain.area;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Polygon {

    public ArrayList<ArrayList<Float>> coordinates;
    public Polygon(ArrayList<ArrayList<Float>> coordinates){
        this.coordinates = coordinates;
    }
}
