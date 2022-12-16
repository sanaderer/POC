package com.github.sanaderer.POC.controller.v1;

import com.github.sanaderer.POC.controller.requests.UserRequest;
import com.github.sanaderer.POC.controller.responses.UserResponse;
import com.github.sanaderer.POC.entity.UserEntity;
import com.github.sanaderer.POC.service.UserService;
import com.github.sanaderer.POC.controller.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.github.sanaderer.POC.controller.mapper.UserMapper.toDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@Valid @RequestBody UserRequest object) {
        return toDto(userService.save(object));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> findAll() {
        List<UserEntity> entityList = userService.findAll();
        return entityList.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findById(@PathVariable UUID id) {
        return toDto(userService.findById(id));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        userService.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponse updateById(@PathVariable UUID id, @RequestBody UserRequest object) {
        return toDto(userService.updateById(id, object));
    }

}
