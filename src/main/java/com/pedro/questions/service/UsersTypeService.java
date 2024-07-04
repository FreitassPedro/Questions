package com.pedro.questions.service;

import com.pedro.questions.entity.UsersType;
import com.pedro.questions.repository.UsersTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersTypeService {

    private final UsersTypeRepository usersTypeRepository;

    public List<UsersType> getAll() {
        return usersTypeRepository.findAll();
    }
}
