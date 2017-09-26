package com.site.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserTestData {
    private final UserRepository userRepository;

    @Autowired
    public UserTestData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initTestData() {
        userRepository.save(new User("admin", "admin"));
        userRepository.save(new User("user", "user"));
        //in real application password should be encrypted
    }
}
