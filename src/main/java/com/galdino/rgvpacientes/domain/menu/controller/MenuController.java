package com.galdino.rgvpacientes.domain.menu.controller;

import com.galdino.rgvpacientes.domain.menu.dto.MenuOut;
import com.galdino.rgvpacientes.domain.menu.dto.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/menus")
public class MenuController {

    private final MenuService menusService;

    public MenuController(MenuService menusService) {
        this.menusService = menusService;
    }

    @GetMapping
    public List<MenuOut> getMenus() {
        return this.menusService.getMenus();
    }

}
