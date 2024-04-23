package com.manage.utils.specification;

import com.manage.model.menu.Menu;
import com.manage.model.sysman.Sysman;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class MenuSpecification {

    public static Specification<Menu> filterBy(String name,String menu_code, String org_menu, String priority,String sysman) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            }
            if (org_menu != null) {
                predicates.add(cb.like(root.get("org_menu"), "%" + org_menu + "%"));
            }
            if (priority != null) {
                predicates.add(cb.like(root.get("priority"), "%" + priority + "%"));
            }
            if (menu_code != null) {
                predicates.add(cb.like(root.get("menu_code"), "%" + menu_code + "%"));
            }
            if (sysman != null) {
                predicates.add(cb.like(root.join("sysman").get("en_name"), "%" + sysman + "%"));
            }

            query.distinct(true);
            Predicate[] predicatesArray = new Predicate[predicates.size()];
            return cb.and(predicates.toArray(predicatesArray));
        };
    }
}

