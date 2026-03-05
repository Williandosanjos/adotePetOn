package br.com.adotepeton.adotepeton.controller;

import br.com.adotepeton.adotepeton.api.petDto.PetCreateRequest;
import br.com.adotepeton.adotepeton.api.petDto.PetResponse;
import br.com.adotepeton.adotepeton.domain.entity.Pet;
import br.com.adotepeton.adotepeton.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetResponse> createPet(@RequestBody @Valid PetCreateRequest request) {
        return ResponseEntity.ok(petService.createPet(request));
    }
}
