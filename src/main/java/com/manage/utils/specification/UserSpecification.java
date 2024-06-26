package com.manage.utils.specification;

import com.manage.model.user.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<User> filterBy(String username, String firstname, String lastname, String nationalCode) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (username != null) {
                predicates.add(cb.like(root.get("username"), "%" + username + "%"));
            }
            if (firstname != null) {
                predicates.add(cb.like(root.get("firstname"), "%" + firstname + "%"));
            }
            if (lastname != null) {
                predicates.add(cb.like(root.get("lastname"), "%" + lastname + "%"));
            }
            if (nationalCode != null) {
                predicates.add(cb.equal(root.get("nationalCode"), nationalCode));
            }

            query.distinct(true);
            Predicate[] predicatesArray = new Predicate[predicates.size()];
            return cb.and(predicates.toArray(predicatesArray));
        };
    }

    public static Specification<User> searchByUsername(String username) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (username != null) {
                predicates.add(cb.like(root.get("username"), "%" + username + "%"));
            }

            query.distinct(true);
            Predicate[] predicatesArray = new Predicate[predicates.size()];
            return cb.and(predicates.toArray(predicatesArray));
        };
    }
}

