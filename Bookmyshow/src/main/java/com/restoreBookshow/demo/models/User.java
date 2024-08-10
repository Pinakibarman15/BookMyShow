package com.restoreBookshow.demo.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Userdata")
public class User extends BaseModel{
    private String username;
    private  String password;
}
