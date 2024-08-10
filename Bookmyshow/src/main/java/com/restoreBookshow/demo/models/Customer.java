package com.restoreBookshow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Builder;

@Entity
@Builder
public class Customer  extends BaseModel{
    private String fullname;
    private String city;
    private String phoneNumber;
    private String email;

    @OneToOne
    private User user;
}
