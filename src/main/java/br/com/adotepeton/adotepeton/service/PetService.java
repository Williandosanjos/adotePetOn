package br.com.adotepeton.adotepeton.service;

import br.com.adotepeton.adotepeton.api.petDto.PetCreateRequest;
import br.com.adotepeton.adotepeton.api.petDto.PetResponse;
import br.com.adotepeton.adotepeton.domain.entity.Pet;
import br.com.adotepeton.adotepeton.domain.entity.User;
import br.com.adotepeton.adotepeton.domain.enums.PetStatus;
import br.com.adotepeton.adotepeton.domain.repository.PetRepository;
import br.com.adotepeton.adotepeton.domain.repository.UserRepository;
import br.com.adotepeton.adotepeton.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public PetResponse createPet(PetCreateRequest request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException ("Usuário autenticado não encontrado"));

        Pet pet = new Pet();
        pet.setName(request.name());
        pet.setSpecies(request.species());
        pet.setBreed(request.breed());
        pet.setAge(request.age());
        pet.setSize(request.size());
        pet.setGender(request.gender());
        pet.setDescription(request.description());
        pet.setNeutered(request.neutered());
        pet.setVaccinated(request.vaccinated());
        pet.setHealthNotes(request.healthNotes());
        pet.setStatus(PetStatus.AVAILABLE);
        pet.setOwner(owner);

        Pet savedPet = petRepository.save(pet);

        return new PetResponse(
                savedPet.getId(),
                savedPet.getName(),
                savedPet.getSpecies(),
                savedPet.getBreed(),
                savedPet.getAge(),
                savedPet.getSize(),
                savedPet.getGender(),
                savedPet.getDescription(),
                savedPet.getNeutered(),
                savedPet.getVaccinated(),
                savedPet.getHealthNotes(),
                savedPet.getStatus().name(),
                owner.getId(),
                owner.getName()
        );
    }
}
