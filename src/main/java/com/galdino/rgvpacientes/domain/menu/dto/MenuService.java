package com.galdino.rgvpacientes.domain.menu.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.galdino.rgvpacientes.domain.menu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galdino.rgvpacientes.domain.menu.repository.MenuRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    public List<MenuOut> getMenus() {
        return menuRepository.getMenus()
                .stream()
                .map(menuMapper::toMenuOut)
                .collect(Collectors.toList());
    }

}
