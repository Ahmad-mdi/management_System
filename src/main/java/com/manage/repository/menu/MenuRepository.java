package com.manage.repository.menu;

import com.manage.model.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> , JpaSpecificationExecutor<Menu> {
    @Query("SELECT COUNT(m) FROM Menu m WHERE m.name LIKE %:name% OR m.org_menu LIKE %:org_menu% OR m.priority LIKE %:priority% OR m.menu_code LIKE %:menu_code%")
    long countBynameOrOrg_menuOrPriorityOrMenu_code(
            @Param("name") String name,
            @Param("org_menu") String org_menu,
            @Param("priority") String priority,
            @Param("menu_code") String menu_code
    );
}
