package com.example.partnersapi.domain.partner;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PartnerResponseDTO {
    String id;
    String tradingName;
    public PartnerResponseDTO(Partner partner){
        this.id = partner.getId();
        this.tradingName = partner.getTradingname();
    }
}
