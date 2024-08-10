package com.restoreBookshow.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCustomerDTO {
    private String fullName;
    private String city;
    private  String phoneNumber;
    private  String email;
    private String password;
}
