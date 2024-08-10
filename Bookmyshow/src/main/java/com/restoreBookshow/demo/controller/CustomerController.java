package com.restoreBookshow.demo.controller;

import com.restoreBookshow.demo.dtos.CreateCustomerDTO;
import com.restoreBookshow.demo.exceptions.InvalidCustomerException;
import com.restoreBookshow.demo.models.Customer;
import com.restoreBookshow.demo.services.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
     private CustomerService customerService;
     //get customer
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerService.getCustomer(id);
    }

    //create a customer
    //post
    @PostMapping
    public Customer createCustomer(@RequestBody CreateCustomerDTO request){
        validate(request);
        return customerService.createCustomer(request);
    }

    private void validate(CreateCustomerDTO request){
        if(request.getEmail()==null)
            throw new InvalidCustomerException();
    }
}
