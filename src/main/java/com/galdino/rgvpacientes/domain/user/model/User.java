package com.galdino.rgvpacientes.domain.user.model;

import com.galdino.rgvpacientes.domain.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(schema = "dbapatient", name = "users")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "dbapatient", name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new ArrayList<>();

    public boolean isNew() {
        return this.getId() == null;
    }

}
