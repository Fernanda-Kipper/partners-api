package com.example.partnersapi.repositories;

import com.example.partnersapi.domain.partner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, String> {
}
