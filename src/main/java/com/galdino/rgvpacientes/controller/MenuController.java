package com.galdino.rgvpacientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galdino.rgvpacientes.model.Menu;
import com.galdino.rgvpacientes.repository.MenuRepository;

@RestController
@RequestMapping("items-menus")
public class MenuController {

    @Autowired
    private MenuRepository itemMenuRepository;

    @GetMapping
    public List<Menu> getMenus() {
        return this.itemMenuRepository.getMenus();
    }

}
