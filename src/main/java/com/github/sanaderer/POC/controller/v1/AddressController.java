package com.github.sanaderer.POC.controller.v1;

import com.github.sanaderer.POC.controller.requests.AddressRequest;
import com.github.sanaderer.POC.controller.responses.AddressResponse;
import com.github.sanaderer.POC.service.AddressService;
import com.github.sanaderer.POC.service.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.github.sanaderer.POC.controller.mapper.AddressMapper.toAddressDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/addresses")
public class AddressController {

    private final AddressService addressService;
    private final CepService cepService;

    @GetMapping("/{cep}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse getAddress(@PathVariable String cep) {
        return cepService.getCep(cep);
    }

    @PostMapping("/{cep}")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse save(@Valid @RequestBody AddressRequest object, @PathVariable String cep) {
        return toAddressDto(addressService.save(object, cep));
    }

}
