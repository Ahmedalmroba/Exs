package com.example.exame.Service;

import com.example.exame.Model.User;
import com.example.exame.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public List<User> getUsers() {
        return authRepository.findAll();
    }
}
