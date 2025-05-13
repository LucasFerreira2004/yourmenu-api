package com.yourmenu.yourmenu_api.businessHours;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, UUID> {
}
