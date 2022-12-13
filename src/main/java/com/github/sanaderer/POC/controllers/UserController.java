package com.github.sanaderer.POC.controllers;

import com.github.sanaderer.POC.controllers.requests.UserRequest;
import com.github.sanaderer.POC.entities.UserEntity;
import com.github.sanaderer.POC.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity save(@Valid @RequestBody UserRequest object) {
        return userService.save(object);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<UserEntity> getById(@PathVariable UUID id) {
        return Optional.ofNullable(userService.findById(id));
    }
}
