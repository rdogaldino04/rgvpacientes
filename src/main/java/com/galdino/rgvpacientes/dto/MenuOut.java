package com.galdino.rgvpacientes.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOut {

    private Integer id;

    private String name;

    private boolean active;

    private String url;

    private MenuOut menuParent;

    private List<MenuOut> subMenus;

}
