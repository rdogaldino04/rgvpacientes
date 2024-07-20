package com.galdino.rgvpacientes.domain.menu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "menus", schema = "dbapatient")
public class Menu {

    @Id
    private Integer id;

    private String name;

    private boolean active;

    private String url;

    @ManyToOne
    @JoinColumn(name = "menu_parent_id")
    @JsonIgnoreProperties("subMenus")
    private Menu menuParent;

    @OneToMany(mappedBy = "menuParent", fetch = FetchType.LAZY)
    private List<Menu> subMenus;

}
