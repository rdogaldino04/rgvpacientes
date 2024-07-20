package com.galdino.rgvpacientes.domain.movementitem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.galdino.rgvpacientes.domain.batch.model.Batch;
import com.galdino.rgvpacientes.domain.movement.model.Movement;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movement_items", schema = "dbapatient")
public class MovementItem {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_item_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movement_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Movement movement;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    private BigInteger quantity;

    @CreationTimestamp
    private OffsetDateTime movementItemDate;

}
