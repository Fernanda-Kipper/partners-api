package com.example.partnersapi.repositories;

import com.example.partnersapi.domain.partner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, String> {
    List<Partner> findAllByCountryAndCity(String country, String city);
}
