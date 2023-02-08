package com.gr8erkay.todotask.services;

import java.util.List;

import com.gr8erkay.todotask.dto.RequestDto;
import com.gr8erkay.todotask.dto.ResponseDto;
import com.gr8erkay.todotask.model.User;

import java.util.List;

    public interface UserService {
        User registerUser(RequestDto requestDto);

        User loginUser(RequestDto requestDto);

    }
