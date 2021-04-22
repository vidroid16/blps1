package com.example.demo.Services;

import com.example.demo.DataBase.UsersDB.User;

public interface UserService {
    //User findUserByToken(String token);
    boolean checkUserIsAuth(String token);

}
