package com.github.sanaderer.POC.controllers.v1;

import com.github.sanaderer.POC.controllers.requests.AddressRequest;
import com.github.sanaderer.POC.controllers.responses.AddressResponse;
import com.github.sanaderer.POC.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.github.sanaderer.POC.controllers.mappers.AddressMapper.toAddressDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/{cep}")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse save(@Valid @RequestBody AddressRequest addressRequest, @PathVariable String cep) {
        return toAddressDto(addressService.save(addressRequest, cep));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse findById(@PathVariable UUID id) {
        return toAddressDto(addressService.findById(id));
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AddressResponse update(@PathVariable UUID id, @RequestBody AddressRequest addressRequest) {
        return toAddressDto(addressService.updateById(id, addressRequest));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        addressService.deleteById(id);
    }

}
