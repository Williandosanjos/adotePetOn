package br.com.adotepeton.adotepeton.domain.entity;

import br.com.adotepeton.adotepeton.domain.enums.PetStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private String breed;
    private Integer age;
    private String size;
    private String gender;

    @Column(length = 1000)
    private String description;

    private Boolean neutered;
    private Boolean vaccinated;
    private String healthNotes;

    @Enumerated(EnumType.STRING)
    private PetStatus status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    private LocalDateTime createdAt;
}
