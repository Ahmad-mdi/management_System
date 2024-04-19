package com.manage.repository.menu;

import com.manage.model.menu.Menu;
import com.manage.model.sysman.Sysman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findBySysman(Sysman sysman);

}
