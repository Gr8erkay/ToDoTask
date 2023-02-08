package com.gr8erkay.todotask.repository;

import com.gr8erkay.todotask.model.Task;
import com.gr8erkay.todotask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);

}
