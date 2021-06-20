package com.example.demo.Controllers;

import com.example.demo.Services.Implementations.DonationServiceImpl;
import com.example.demo.socket.SocketMessage;
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
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    public ResponseEntity test() throws InterruptedException, ExecutionException, TimeoutException {
//        WebSocketClient client = new StandardWebSocketClient();
//
//        WebSocketStompClient stompClient = new WebSocketStompClient(client);
//        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//
//        StompSessionHandler sessionHandler = new StompSessionHandler() {
//            @Override
//            public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
//
//            }
//
//            @Override
//            public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
//
//            }
//
//            @Override
//            public void handleTransportError(StompSession stompSession, Throwable throwable) {
//
//            }
//
//            @Override
//            public Type getPayloadType(StompHeaders stompHeaders) {
//                return null;
//            }
//
//            @Override
//            public void handleFrame(StompHeaders stompHeaders, Object o) {
//                SocketMessage message = (SocketMessage) o;
//                System.out.println("Message was received with content: " + message.getContent());
//            }
//        };
//        StompSession stompSession = stompClient.connect("ws://localhost:17886/websocketkick", sessionHandler).get(10, TimeUnit.SECONDS);
//
//        stompSession.subscribe("/topic/messages", sessionHandler);
//        SocketMessage message = new SocketMessage();
//        message.setContent("Hello world!");
//        stompSession.send("/chat.sendMessage", message);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Задонатить деньги")
    @PostMapping(path ="/donate")
    public String donate(@RequestParam(value = "login") String login, @RequestParam(value = "project_id") Long project_id, @RequestParam(value = "card") String cardNumber, @RequestParam(value = "sum") int sum) throws IOException {
        int donated = donationService.donate(login, project_id, sum, cardNumber);
        if(donated == 200) return "Операция произведена успешно";
        if(donated == 400) return "Не достаточно средств или некорректно введены данные карты";
        return "Что-то пошло не так";
    }

    @PostMapping(path ="/Tr")
    public int testTr(){
        donationService.testTr();
        return 12;
    }

}
