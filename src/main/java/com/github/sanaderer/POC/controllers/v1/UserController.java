package com.github.sanaderer.POC.controllers.v1;

import com.github.sanaderer.POC.controllers.mappers.AddressMapper;
import com.github.sanaderer.POC.controllers.mappers.UserMapper;
import com.github.sanaderer.POC.controllers.requests.UserRequest;
import com.github.sanaderer.POC.controllers.responses.AddressResponse;
import com.github.sanaderer.POC.controllers.responses.UserResponse;
import com.github.sanaderer.POC.entities.AddressEntity;
import com.github.sanaderer.POC.entities.UserEntity;
import com.github.sanaderer.POC.enums.UserEnum;
import com.github.sanaderer.POC.services.UserService;
import com.github.sanaderer.POC.services.impl.UserServiceImpl;
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
import java.util.stream.Collectors;

import static com.github.sanaderer.POC.controllers.mappers.UserMapper.toDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@Valid @RequestBody UserRequest userRequest) {
        return toDto(userService.save(userRequest));
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findById(@PathVariable UUID id) {
        return toDto(userService.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponse> findAll(@PageableDefault(size = 5, direction = Sort.Direction.ASC, sort = "id") Pageable pageable) {
        Page<UserEntity> page = userService.findAll(pageable);
        return page.map(UserMapper::toDto);
    }

    @GetMapping(path = "/addresses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AddressResponse> findAddressByUserId(@PathVariable UUID id) {
        List<AddressEntity> addresses = userServiceImpl.findAddressByUserId(id);
        return addresses.stream().map(AddressMapper::toAddressDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponse> findByDocumentType(@RequestParam String documentType, @PageableDefault(size = 5, direction = Sort.Direction.ASC, sort = "id") Pageable pageable) {
        UserEnum userEnum = UserEnum.valueOf(documentType.toUpperCase());
        Page<UserEntity> users = userService.findByDocumentType(userEnum, pageable);
        return users.map(UserMapper::toDto);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponse updateById(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        return toDto(userService.updateById(id, userRequest));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        userService.deleteById(id);
    }

}
