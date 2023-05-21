package com.example.partnersapi.domain.partner;

import com.example.partnersapi.domain.address.Address;
import com.example.partnersapi.domain.area.CoverageArea;
import jakarta.validation.constraints.NotBlank;

public record PartnerRequestDTO(
    @NotBlank
    String tradingName,
    @NotBlank
    String ownerName,
    @NotBlank
    String document,
    Address address,

    CoverageArea coverageArea
){};
