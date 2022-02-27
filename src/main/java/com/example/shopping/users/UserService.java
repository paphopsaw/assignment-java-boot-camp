package com.example.shopping.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(int id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException(id);
    }
}
