package com.github.sanaderer.POC.controller.v1;

import com.github.sanaderer.POC.controller.mapper.UserMapper;
import com.github.sanaderer.POC.controller.requests.UserRequest;
import com.github.sanaderer.POC.controller.responses.UserResponse;
import com.github.sanaderer.POC.entity.UserEntity;
import com.github.sanaderer.POC.enums.UserEnum;
import com.github.sanaderer.POC.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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
    public Page<UserResponse> findAll(@PageableDefault(size = 5, direction = Sort.Direction.ASC, sort = "id") Pageable pageable) {
        Page<UserEntity> page = userService.findAll(pageable);
        return page.map(UserMapper::toDto);
    }

    @GetMapping(path = "/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserEntity> findByType(@RequestParam String documentType, @PageableDefault(size = 5, direction = Sort.Direction.ASC, sort = "id") Pageable pageable) {
        UserEnum userEnum = UserEnum.valueOf(documentType.toUpperCase());
        Page<UserEntity> users = userService.findByDocumentType(userEnum, pageable);
        return users;
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
