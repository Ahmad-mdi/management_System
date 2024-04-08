package com.manage.utils.specification;

import com.manage.model.system_management.SM;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SMSpecification {

    public static Specification<SM> filterBy(String en_name, String fa_name, String route) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (en_name != null) {
                predicates.add(cb.like(root.get("en_name"), "%" + en_name + "%"));
            }
            if (fa_name != null) {
                predicates.add(cb.like(root.get("fa_name"), "%" + fa_name + "%"));
            }
            if (route != null) {
                predicates.add(cb.like(root.get("route"), "%" + route + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

