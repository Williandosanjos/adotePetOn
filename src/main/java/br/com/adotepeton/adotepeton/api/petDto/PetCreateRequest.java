package br.com.adotepeton.adotepeton.api.petDto;

import jakarta.validation.constraints.NotBlank;

public record PetCreateRequest(
        @NotBlank
        String name,

        @NotBlank
        String species,

        String breed,

        Integer age,

        String size,

        String gender,

        String description,

        Boolean neutered,

        Boolean vaccinated,

        String healthNotes
) {}
