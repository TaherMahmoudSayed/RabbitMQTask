package com.microservice.controller;

import com.microservice.requestResponceDTO.CustomerRegistrationRequestDTO;
import com.microservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/customer")
@RequiredArgsConstructor

public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("add")
    public CustomerRegistrationRequestDTO register(@RequestBody CustomerRegistrationRequestDTO customerDTO){
        return customerService.registerCustomer(customerDTO);
    }

}
