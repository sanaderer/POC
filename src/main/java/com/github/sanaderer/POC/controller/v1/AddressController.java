package com.github.sanaderer.POC.controller.v1;

import com.github.sanaderer.POC.controller.requests.AddressRequest;
import com.github.sanaderer.POC.controller.responses.AddressResponse;
import com.github.sanaderer.POC.entity.AddressEntity;
import com.github.sanaderer.POC.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.github.sanaderer.POC.controller.mapper.AddressMapper.toAddressDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/{cep}")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse save(@Valid @RequestBody AddressRequest object, @PathVariable String cep, AddressEntity addressEntity) {
        return toAddressDto(addressService.save(object, cep, addressEntity));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse findById(@PathVariable UUID id){
        return toAddressDto(addressService.findById(id));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id){
        addressService.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AddressResponse update(@PathVariable UUID id, @RequestBody AddressRequest addressRequest){
        return toAddressDto(addressService.updateById(id, addressRequest));
    }

}
