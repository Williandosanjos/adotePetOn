package br.com.adotepeton.adotepeton.domain.repository;

import br.com.adotepeton.adotepeton.domain.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository  extends JpaRepository<Pet, Long> {
}
