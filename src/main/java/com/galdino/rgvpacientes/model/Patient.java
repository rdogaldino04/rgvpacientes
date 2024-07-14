package com.galdino.rgvpacientes.model;

import com.galdino.rgvpacientes.enums.Status;
import com.galdino.rgvpacientes.enums.converters.StatusConverter;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "patients", schema = "dbapatient")
@SQLDelete(sql = "UPDATE patients SET status = 'Inactive' WHERE id = ?")
public class Patient {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String name;

    private String phone;

    @Embedded
    private Address address;

    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

}
