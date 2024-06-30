package com.galdino.rgvpacientes.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "movements", schema = "dbapatient")
public class Movement {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id")
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Sector sector;

    @ManyToOne
    private Stock stock;

    @NotNull
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "movement")
    private List<MovementItem> items = new ArrayList<>();

    @CreationTimestamp
    private OffsetDateTime movementDate;

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

}
