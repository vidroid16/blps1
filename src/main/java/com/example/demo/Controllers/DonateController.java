package com.example.demo.Controllers;

import com.example.demo.DataBase.DonationsDB.Donation;
import com.example.demo.DataBase.DonationsDB.DonationBody;
import com.example.demo.DataBase.DonationsDB.DonationsRepository;
import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.ProjectsDB.ProjectRepository;
import com.example.demo.DataBase.UsersDB.User;
import com.example.demo.DataBase.UsersDB.UsersRepository;
import com.example.demo.JsonResponse;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class DonateController {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    DonationsRepository donationsRepository;
    @PostMapping("/test")
    public MyResponse test(String data){
        System.out.println(data);
        return new MyResponse(200, "OK!");
    }

    @PostMapping("/check")
    public MyResponse isAuth(String token){
        try{
            System.out.println(token);
            String res = usersRepository.findByToken(token).getToken();
            return new MyResponse(200, "ok");
        }catch (NullPointerException e){
            return new MyResponse(404, "Пожалуйста авторизируйтесь в нашей системе");

        }
    }

    @PostMapping("/donate")
    @Transactional
    public MyResponse donate(@RequestParam(value = "token") String token, @RequestParam(value = "project_id") Long project_id, @RequestParam(value = "sum") int sum){
        try {
            User u = usersRepository.findByToken(token);
            System.out.println(u.getLogin());
            Project p = projectRepository.findById(project_id).get();
            donationsRepository.save(new Donation(u,p,sum));
            projectRepository.donate(sum,project_id);
        }catch (NullPointerException e){
            return new MyResponse(404,"Нет такого пользователя или проекта");
        }
        return new MyResponse(200,""+sum);
    }
}
