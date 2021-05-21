package com.example.demo.Controllers;

import com.example.demo.Services.Implementations.DonationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.*;

@RestController
public class DonateController {
    private final DonationServiceImpl donationService;

    @Autowired
    public DonateController(DonationServiceImpl donationService) {
        this.donationService = donationService;
    }

    @Autowired

    @Operation(summary = "Тестовый ендпоинт")
    @PostMapping("/test")
    public ResponseEntity test(){
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Задонатить деньги")
    @PostMapping(path ="/donate")
    @Transactional
    public ResponseEntity donate(@RequestParam(value = "login") String login, @RequestParam(value = "project_id") Long project_id, @RequestParam(value = "card") String cardNumber, @RequestParam(value = "sum") int sum) throws IOException {
        boolean isDonated = donationService.donate(login, project_id, sum);
        if(isDonated){
            HttpPost post = new HttpPost("http://localhost:18110/bank/donate");
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
