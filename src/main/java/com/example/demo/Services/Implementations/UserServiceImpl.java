package com.example.demo.Services.Implementations;

import com.example.demo.Controllers.MyResponse;
import com.example.demo.DataBase.UsersDB.UsersRepository;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean checkUserIsAuth(String token) {
        try{
            String res = usersRepository.findByToken(token).getToken();
            return true;
        }catch (NullPointerException e){
            return false;

        }
    }
}
