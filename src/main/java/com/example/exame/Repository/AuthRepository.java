package com.example.exame.Repository;

import com.example.exame.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository <User,Integer> {
    User findUsersById(Integer id);
}
