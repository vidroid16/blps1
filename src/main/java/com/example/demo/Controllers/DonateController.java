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
import io.swagger.v3.oas.annotations.Operation;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DonateController {
    private final UserServiceImpl userService;
    private final DonationServiceImpl donationService;

    @Autowired
    public DonateController(UserServiceImpl userService, DonationServiceImpl donationService) {
        this.userService = userService;
        this.donationService = donationService;
    }

    @Autowired

    @Operation(summary = "Тестовый ендпоинт")
    @PostMapping("/test")
    public ResponseEntity test(){
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Проверка авторизации пользователя")
    @PostMapping("/check")
    public ResponseEntity isAuth(@RequestParam(value = "token") String token){
        boolean isAuth = userService.checkUserIsAuth(token);
        if(isAuth){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().body("{\"response\":\"Пожалуйста авторизируйтесь в нашей системе\"}");
        }
    }

    @Operation(summary = "Задонатить деньги")
    @PostMapping(path ="/donate")
    @Transactional
    public ResponseEntity donate(@RequestParam(value = "token") String token, @RequestParam(value = "project_id") Long project_id, @RequestParam(value = "card") String cardNumber, @RequestParam(value = "sum") int sum) throws IOException {
        boolean isDonated = donationService.donate(token, project_id, sum);
        if(isDonated){
            HttpPost post = new HttpPost("http://localhost:8081/bank/donate");
            JSONObject requestBody = new JSONObject();
            requestBody.put("number", cardNumber);
            requestBody.put("money", String.valueOf(sum));
            post.setHeader("content-type", "application/json");
            post.setEntity(new StringEntity(requestBody.toString()));
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)){
                if (response.getStatusLine().getStatusCode() != 200) {
                    TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                    return ResponseEntity.status(response.getStatusLine().getStatusCode()).body(EntityUtils.toString(response.getEntity()));
                }
                return ResponseEntity.status(response.getStatusLine().getStatusCode()).body(EntityUtils.toString(response.getEntity()));
            }
//            return new MyResponse(200,""+sum);
        }else {
            return ResponseEntity.badRequest().body("{\"response\":\"Нет такого пользователя или проекта!\"}");
        }
    }

}
