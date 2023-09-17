package com.galdino.rgvpacientes.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "materials", schema = "dbapatient")
public class Material {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotNull
    @FutureOrPresent
    private LocalDate expirationDate;

    @CreationTimestamp
    private LocalDate registrationDate;

    public Material() {
    }

    public Material(Long id) {
        this.id = id;
    }
}
