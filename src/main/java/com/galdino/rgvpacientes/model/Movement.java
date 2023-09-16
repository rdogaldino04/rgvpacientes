package com.galdino.rgvpacientes.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

//@Data
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "movements", schema = "dbapatient")
public class Movement {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "movement")
    private List<MovementItem> items = new ArrayList<>();

    @CreationTimestamp
    private OffsetDateTime registrationDate;

    public void addItem(MovementItem item) {
        if (item != null) {
            items.add(item);
        }
    }

}
