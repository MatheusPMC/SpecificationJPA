package com.specification.specification.repository.spec;

import com.specification.specification.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public Specification<UserEntity> nameLike(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name));
    }
}