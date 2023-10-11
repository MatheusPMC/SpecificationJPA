package com.specification.specification.controller;

import com.specification.specification.entity.UserEntity;
import com.specification.specification.repository.UserRepository;
import com.specification.specification.repository.spec.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    @GetMapping("/1/")
    public UserEntity find() {
        Specification<UserEntity> spec = Specification.where(null);
        var user = repository.findOne(spec.and(new UserSpecification().nameLike("test")));
        return user.get();
    }

    @GetMapping("/2/")
    public long find2() {
        Specification<UserEntity> spec = Specification.where(null);
        String name = "MaTheuS";
        // IGNORE CASE ->
        // Size ->
        var user = repository.findAll(spec.and(new UserSpecification().nameLike("%" + name.toLowerCase() + "%"))).size();
        return user;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity created(@RequestBody UserEntity userModel) {
        return repository.save(userModel);
    }
}