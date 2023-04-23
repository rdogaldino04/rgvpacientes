package com.galdino.rgvpacientes.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galdino.rgvpacientes.dto.mapper.MenuMapper;
import com.galdino.rgvpacientes.repository.MenuRepository;

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
