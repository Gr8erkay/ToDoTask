package com.gr8erkay.todotask.services.implementation;

import com.gr8erkay.todotask.dto.RequestDto;
import com.gr8erkay.todotask.dto.ResponseDto;
import com.gr8erkay.todotask.model.User;
import com.gr8erkay.todotask.repository.UserRepository;
import com.gr8erkay.todotask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(RequestDto requestDto) {
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmailAddress(requestDto.getEmailAddress());
        user.setPassword(requestDto.getPassword());
//        user.setUsername(requestDto.getUsername());
        return userRepository.save(user);
    }

    @Override
    public User loginUser(RequestDto requestDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findUserByEmailAndPassword(requestDto.getEmailAddress(), requestDto.getPassword()));
        ResponseDto response = new ResponseDto();

        if(user.isPresent()) {
            response.setStatusCode(true);
            response.setUser(user.get());
            response.setMessage("User Login successful");
            return response.getUser();
        } else {
            response.setMessage("Invalid Login");
            return response.getData();
        }
    }
}
