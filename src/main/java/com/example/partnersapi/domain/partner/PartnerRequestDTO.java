package com.example.partnersapi.domain.partner;

import com.example.partnersapi.domain.address.AddressDTO;
import com.example.partnersapi.domain.area.CoverageAreaDTO;
import jakarta.validation.constraints.NotBlank;

public record PartnerRequestDTO(
    @NotBlank
    String tradingName,
    @NotBlank
    String ownerName,
    @NotBlank
    String document,
    AddressDTO address,

    CoverageAreaDTO coverageArea
){};
