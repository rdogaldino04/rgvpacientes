package com.galdino.rgvpacientes.domain.menu.mapper;

import com.galdino.rgvpacientes.domain.menu.dto.MenuOut;
import com.galdino.rgvpacientes.domain.menu.model.Menu;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuMapper {

    public MenuOut toMenuOut(Menu menu) {
        if (menu == null) {
            return null;
        }

        MenuOut menuParent = toMenuParentOut(menu);
        List<MenuOut> subMenus = toSubMenusOut(menu.getSubMenus());

        return MenuOut.builder()
                .id(menu.getId())
                .name(menu.getName())
                .active(menu.isActive())
                .url(menu.getUrl())
                .menuParent(menuParent)
                .subMenus(subMenus)
                .build();
    }

    private MenuOut toMenuParentOut(Menu menu) {
        if (menu.getMenuParent() != null) {
            return MenuOut
                    .builder()
                    .id(menu.getMenuParent().getId())
                    .name(menu.getMenuParent().getName())
                    .active(menu.getMenuParent().isActive())
                    .url(menu.getMenuParent().getUrl())
                    .build();
        }
        return null;
    }

    private List<MenuOut> toSubMenusOut(List<Menu> menus) {
        if (!menus.isEmpty()) {
            return menus.stream()
                    .map(menu -> MenuOut.builder()
                            .id(menu.getId())
                            .name(menu.getName())
                            .active(menu.isActive())
                            .url(menu.getUrl())
                            .menuParent(toMenuParentOut(menu))
                            .subMenus(new ArrayList<>())
                            .build())
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
