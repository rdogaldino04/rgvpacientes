package com.galdino.rgvpacientes.domain.batch.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.galdino.rgvpacientes.domain.product.model.Product;
import lombok.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Builder
@Table(name = "batch", schema = "dbapatient")
public class Batch {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_id")
    private Long id;

    @NotBlank
    private String batchNumber;

    @NotNull
    private LocalDate manufactureDate;

    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Batch() {
    }

    public Batch(Long id) {
        this.id = id;
    }
}
