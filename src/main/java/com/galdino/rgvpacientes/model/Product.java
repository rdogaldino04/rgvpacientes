package com.galdino.rgvpacientes.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "product", schema = "dbapatient")
public class Product {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Deprecated
    @NotNull
    @FutureOrPresent
    private LocalDate expirationDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }
}
