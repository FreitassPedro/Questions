package com.pedro.questions.service;

import com.pedro.questions.entity.Users;
import com.pedro.questions.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public Users save(Users users) {
        return usersRepository.save(users);
    }
}
