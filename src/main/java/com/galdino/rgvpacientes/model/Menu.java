package com.galdino.rgvpacientes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "menus")
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
