package com.galdino.rgvpacientes.model;

import com.galdino.rgvpacientes.enums.MovementName;
import com.galdino.rgvpacientes.enums.MovementType;
import com.galdino.rgvpacientes.exception.BusinessException;
import com.galdino.rgvpacientes.user.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movements", schema = "dbapatient")
public class Movement {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id")
    private Long id;

    @NotNull
    @ManyToOne
    private Patient patient;

    @NotNull
    @ManyToOne
    private Stock stockSourceLocation;

    @ManyToOne
    private Stock stockDestinationLocation;

    @NotNull
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "movement")
    private List<MovementItem> items = new ArrayList<>();

    @CreationTimestamp
    private OffsetDateTime movementDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    @Valid
    @NotNull
    @Enumerated(EnumType.STRING)
    private MovementName name;

    @ManyToOne
    @JoinColumn(name = "related_movement_id", nullable = true)
    private Movement relatedMovement;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String observation;

    public void addItem(MovementItem item) {
        if (item != null) {
            items.add(item);
            item.setMovement(this);
        }
    }

    public void removeItem(MovementItem item) {
        if (item != null) {
            items.remove(item);
            item.setMovement(null);
        }
    }

    public List<MovementItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void removeItems() {
        items.forEach(item -> item.setMovement(null));
        items.clear();
    }

    public void validateNoDuplicateBatchIds() {
        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).getBatch().equals(items.get(j).getBatch())) {
                    throw new EntityNotFoundException(
                            "There are movement items with the same batch id: " + items.get(i).getBatch().getId());
                }
            }
        }
    }

    public boolean hasItems() {
        return items != null && !items.isEmpty();
    }

    public void setName(@NotNull MovementName name) {
        if (movementType == MovementType.OUTPUT && name == MovementName.ENTRADA_AVULSA) {
            throw new BusinessException("Movement name must be OUTPUT for movement type ENTRADA_AVULSA");
        }

        if (movementType == MovementType.INPUT && name == MovementName.SAIDA_AVULSA) {
            throw new BusinessException("Movement name must be INPUT for movement type SAIDA_AVULSA");
        }

        this.name = name;
    }
}
