package com.galdino.rgvpacientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galdino.rgvpacientes.dto.MenuOut;
import com.galdino.rgvpacientes.dto.MenuService;

@RestController
@RequestMapping("menus")
public class MenuController {

    @Autowired
    private MenuService menusService;

    @GetMapping
    public List<MenuOut> getMenus() {
        return this.menusService.getMenus();
    }

}
