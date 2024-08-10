package com.restoreBookshow.demo.services;

import com.restoreBookshow.demo.dtos.CreateCustomerDTO;
import com.restoreBookshow.demo.exceptions.CustomerNotFoundException;
import com.restoreBookshow.demo.exceptions.EmailAllreadyExistsException;
import com.restoreBookshow.demo.models.Customer;
import com.restoreBookshow.demo.models.User;
import com.restoreBookshow.demo.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;
    private UserService userService;


    public Customer getCustomer(Long id){
        return customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
    }


    public Customer createCustomer(CreateCustomerDTO request){
        String email=request.getEmail();
        Optional<Customer> existingCustomer=customerRepository.findCustomerByEmail(email);
        if(existingCustomer.isPresent()){
            throw new EmailAllreadyExistsException(email);
        }

        User user=userService.createUser(request.getFullName(),request.getPassword());
        Customer customer=Customer.builder().city(request.getCity())
                        .email(request.getEmail())
                                .phoneNumber(request.getPhoneNumber())
                                        .user(user)
                                                .fullname(request.getFullName()).build();
         return customerRepository.save(customer);


    }

    public Customer  getCustomerInternal(Long userId){
        return customerRepository.findById(userId).orElse(null);
    }
}
