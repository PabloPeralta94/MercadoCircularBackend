package com.mercado.circular.service;

import com.mercado.circular.model.FishingGear;
import com.mercado.circular.repository.FishingGearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishingGearService {

    private final FishingGearRepository fishingGearRepository;

    @Autowired
    public FishingGearService(FishingGearRepository fishingGearRepository) {
        this.fishingGearRepository = fishingGearRepository;
    }

    public List<FishingGear> getAllFishingGear() {
        return fishingGearRepository.findAll();
    }

    public FishingGear getFishingGearById(Long id) {
        return fishingGearRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    public FishingGear createFishingGear(FishingGear fishingGear) {
        return fishingGearRepository.save(fishingGear);
    }

    public FishingGear updateFishingGear(Long id, FishingGear updatedGear) {
        if (!fishingGearRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        updatedGear.setId(id);
        return fishingGearRepository.save(updatedGear);
    }

    public void deleteFishingGear(Long id) {
        if (!fishingGearRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        fishingGearRepository.deleteById(id);
    }
}