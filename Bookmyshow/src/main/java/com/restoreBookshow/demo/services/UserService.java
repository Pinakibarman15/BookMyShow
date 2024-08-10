package com.restoreBookshow.demo.services;

import com.restoreBookshow.demo.models.User;
import com.restoreBookshow.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    public User createUser(String username, String passwrd){
        User user=new User(username,passwrd);
        return userRepository.save(user);
    }
}
