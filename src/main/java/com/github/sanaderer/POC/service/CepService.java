package com.github.sanaderer.POC.service;

import com.github.sanaderer.POC.controller.responses.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(url= "https://viacep.com.br/ws/" , name = "viacep")
public interface CepService {

    @GetMapping("/{cep}/json")
    AddressResponse getCep(@PathVariable("cep") String cep);

}
