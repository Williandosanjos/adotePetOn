package br.com.adotepeton.adotepeton.api.petDto;

public record PetResponse(
        Long id,
        String name,
        String species,
        String breed,
        Integer age,
        String size,
        String gender,
        String description,
        Boolean neutered,
        Boolean vaccinated,
        String healthNotes,
        String status,
        Long ownerId,
        String ownerName
) {
}
