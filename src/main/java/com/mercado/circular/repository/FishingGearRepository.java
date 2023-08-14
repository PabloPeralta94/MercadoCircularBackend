package com.mercado.circular.repository;

import com.mercado.circular.model.FishingGear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishingGearRepository extends JpaRepository<FishingGear, Long> {
}