package com.example.demo.DataBase.UsersDB;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long>{
    Optional<User> findByLogin(String login);
    Optional<User> findByLoginAndPassword(String login, String password);
    @Query(
            value = "SELECT * FROM blps_users",
            nativeQuery = true)
    ArrayList<User> getall();
}