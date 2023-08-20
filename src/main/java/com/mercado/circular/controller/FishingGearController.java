package com.mercado.circular.controller;
import com.mercado.circular.model.FishingGear;
import com.mercado.circular.model.Purchase;
import com.mercado.circular.security.entity.Usuario;
import com.mercado.circular.security.jwt.JwtProvider;
import com.mercado.circular.security.service.UsuarioService;
import com.mercado.circular.service.FishingGearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/api/fishing-gear")
@CrossOrigin(origins = "http://localhost:4200")
public class FishingGearController {

    private final FishingGearService fishingGearService;
    private final UsuarioService usuarioService;
    private final JwtProvider jwtProvider;

    @Autowired
    public FishingGearController(FishingGearService fishingGearService, UsuarioService usuarioService, JwtProvider jwtProvider) {
        this.fishingGearService = fishingGearService;
        this.usuarioService = usuarioService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping
    public ResponseEntity<List<FishingGear>> getAllFishingGear() {
        List<FishingGear> gearList = fishingGearService.getAllFishingGear();
        return ResponseEntity.ok(gearList);
    }

    @GetMapping("/{gearId}")
    public ResponseEntity<FishingGear> getFishingGearById(@PathVariable Long gearId) {
        FishingGear gear = fishingGearService.getFishingGearById(gearId);
        return ResponseEntity.ok(gear);
    }

    @PostMapping("/byUser")
    public ResponseEntity<FishingGear> createFishingGearForUsuario(@Valid @RequestBody FishingGear gear,
                                                                  Authentication authentication) {

        String nombreUsuario = authentication.getName();

        Optional<Usuario> existingUsuario = usuarioService.getByNombreUsuario(nombreUsuario);
        if (existingUsuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        gear.setUser(existingUsuario.get());
        FishingGear createdGear = fishingGearService.createFishingGear(gear);
        return new ResponseEntity<>(createdGear, HttpStatus.CREATED);
    }

    @PutMapping("/{gearId}")
    public ResponseEntity<FishingGear> updateFishingGear(@PathVariable Long gearId, @Valid @RequestBody FishingGear updatedGear) {
        Optional<FishingGear> existingGear = Optional.ofNullable(fishingGearService.getFishingGearById(gearId));
        if (existingGear.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedGear.setId(gearId);
        FishingGear updatedGearResult = fishingGearService.updateFishingGear(gearId, updatedGear);
        return new ResponseEntity<>(updatedGearResult, HttpStatus.OK);
    }

    @DeleteMapping("/{gearId}")
    public ResponseEntity<String> deleteFishingGear(@PathVariable Long gearId) {
        Optional<FishingGear> gear = Optional.ofNullable(fishingGearService.getFishingGearById(gearId));
        if (gear.isEmpty()) {
            return new ResponseEntity<>("Fishing gear not found", HttpStatus.NOT_FOUND);
        }

        fishingGearService.deleteFishingGear(gearId);
        return new ResponseEntity<>("Fishing gear deleted successfully", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/buy/{gearId}")
    public ResponseEntity<String> buyFishingGear(@PathVariable Long gearId, @RequestBody Purchase purchase, Authentication authentication) {
        String nombreUsuario = authentication.getName();

        Optional<Usuario> existingUsuario = usuarioService.getByNombreUsuario(nombreUsuario);
        Optional<FishingGear> existingGear = Optional.ofNullable(fishingGearService.getFishingGearById(gearId));

        if (existingUsuario.isEmpty() || existingGear.isEmpty()) {
            return new ResponseEntity<>("User or gear not found", HttpStatus.NOT_FOUND);
        }

        // aca iria la logica de la compra

        return new ResponseEntity<>("Purchase successful", HttpStatus.OK);
    }


}