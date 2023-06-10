package com.example.partnersapi.domain.address;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record AddressDTO(
        @NotNull
        String type,
        @NotEmpty
        ArrayList<Float> coordinates
        /* X, Y = Long - Lat */
){};
