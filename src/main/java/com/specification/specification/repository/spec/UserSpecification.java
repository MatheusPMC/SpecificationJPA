package com.specification.specification.repository.spec;

import com.specification.specification.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {

    public Specification<UserEntity> nameLike(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name));
    }

    public Specification<UserEntity> getUsers(UserEntity user) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (user.getName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), user.getName()));
            }
            if (user.getUsername() != null) {
                predicates.add(criteriaBuilder.equal(root.get("username"), user.getUsername()));
            }

            query.orderBy(criteriaBuilder.desc(root.get("createdAt")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}