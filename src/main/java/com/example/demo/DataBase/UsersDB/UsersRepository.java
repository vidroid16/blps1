package com.example.demo.DataBase.UsersDB;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long>{
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    User findByToken(String token);
}