package com.example.partnersapi.domain.area;

import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;

public record CoverageArea(
    @NotBlank
    String type,
    ArrayList<ArrayList<ArrayList<ArrayList<Float>>>> coordinates
){};
