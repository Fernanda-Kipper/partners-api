package com.example.partnersapi.domain.area;

import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;

public record CoverageAreaDTO(
    @NotBlank
    String type,
    ArrayList<ArrayList<ArrayList<ArrayList<Float>>>> coordinates
){};
