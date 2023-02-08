package com.gr8erkay.todotask.dto;


import com.gr8erkay.todotask.enums.Status;
import com.gr8erkay.todotask.model.User;
import lombok.Data;

import java.util.List;
@Data
public class ResponseDto {

        private String message;
        private User user;
        private List<User> userList;
        private User data;
        private boolean statusCode;

        private String firstName;

        private String lastName;

        private String emailAddress;

        private String gender;
        private Status status;

        private String title;

        private String description;
}

