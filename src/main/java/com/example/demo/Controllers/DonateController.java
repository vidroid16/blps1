package com.example.demo.Controllers;

import com.example.demo.DataBase.DonationsDB.Donation;
import com.example.demo.DataBase.DonationsDB.DonationBody;
import com.example.demo.DataBase.DonationsDB.DonationsRepository;
import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.ProjectsDB.ProjectRepository;
import com.example.demo.DataBase.UsersDB.User;
import com.example.demo.DataBase.UsersDB.UsersRepository;
import com.example.demo.JsonResponse;
import com.example.demo.Services.Implementations.DonationServiceImpl;
import com.example.demo.Services.Implementations.UserServiceImpl;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class DonateController {
    private final UserServiceImpl userService;
    private final DonationServiceImpl donationService;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    DonationsRepository donationsRepository;

    @Autowired
    public DonateController(UserServiceImpl userService, DonationServiceImpl donationService) {
        this.userService = userService;
        this.donationService = donationService;
    }

    @Autowired

    @PostMapping("/test")
    public MyResponse test(){
        return new MyResponse(200, "OK!");
    }

    @PostMapping("/check")
    public MyResponse isAuth(String token){
        boolean isAuth = userService.checkUserIsAuth(token);
        if(isAuth){
            return new MyResponse(200, "ok");
        }else {
            return new MyResponse(404, "Пожалуйста авторизируйтесь в нашей системе");
        }
    }

    @PostMapping("/donate")
    @Transactional
    public MyResponse donate(@RequestParam(value = "token") String token, @RequestParam(value = "project_id") Long project_id, @RequestParam(value = "sum") int sum){
        boolean isDonated = donationService.donate(token, project_id, sum);
        if(isDonated){
            return new MyResponse(404,"Нет такого пользователя или проекта");
        }else {
            return new MyResponse(200,""+sum);
        }
    }
}
