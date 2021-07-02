package com.example.demo.Services.Implementations;

import com.example.demo.Controllers.MyResponse;
import com.example.demo.DataBase.DonationsDB.Donation;
import com.example.demo.DataBase.DonationsDB.DonationsRepository;
import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.ProjectsDB.ProjectRepository;
import com.example.demo.DataBase.UsersDB.User;
import com.example.demo.DataBase.UsersDB.UsersRepository;
import com.example.demo.Services.DonationService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@Service
public class DonationServiceImpl implements DonationService {
    private final UsersRepository usersRepository;
    private final ProjectRepository projectRepository;
    private final DonationsRepository donationsRepository;
    private final PlatformTransactionManager transactionManager;
    @Value("${bankServer.port}")
    private String bankServerPort;


    @Autowired
    public DonationServiceImpl(UsersRepository usersRepository,
                               ProjectRepository projectRepository,
                               DonationsRepository donationsRepository,
                               PlatformTransactionManager transactionManager
    ) {
        this.usersRepository = usersRepository;
        this.projectRepository = projectRepository;
        this.donationsRepository = donationsRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public int donate(String login, Long project_id, int sum, String cardNumber) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("Donation Transaction"); // Transaction name
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            User u = usersRepository.findByLogin(login).orElse(null);
            if (u == null)
                return 500;
            Project p = projectRepository.findById(project_id).get();
            if (p == null)
                return 500;
            donationsRepository.save(new Donation(u,p,sum));
            projectRepository.donate(sum, project_id);
            HttpPost post = new HttpPost("http://localhost:" + this.bankServerPort + "/bank/donate");
            JSONObject requestBody = new JSONObject();
            requestBody.put("number", cardNumber);
            requestBody.put("money", String.valueOf(sum));
            post.setHeader("content-type", "application/json");
            post.setEntity(new StringEntity(requestBody.toString()));
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode()!=200){
                 transactionManager.rollback(status);
                return 400;
            }
        }catch (Exception e){
            transactionManager.rollback(status);
            return 501;
        }
        transactionManager.commit(status);
        return 200;
    }

    @Override
    public int donate(Long project_id, int sum) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("Donation Transaction"); // Transaction name
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            Project p = projectRepository.findById(project_id).get();
            if (p == null)
                return 500;
            projectRepository.donate(sum, project_id);
            HttpPost post = new HttpPost("http://localhost:" + this.bankServerPort + "/bank/donate");
            JSONObject requestBody = new JSONObject();
            requestBody.put("number", p.getCard());
            requestBody.put("money", String.valueOf(sum));
            post.setHeader("content-type", "application/json");
            post.setEntity(new StringEntity(requestBody.toString()));
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode()!=200){
                transactionManager.rollback(status);
                return 400;
            }
        }catch (Exception e){
            transactionManager.rollback(status);
            return 501;
        }
        System.out.println("ROLLBACK");
        transactionManager.commit(status);
        return 200;
    }

    @Override
    public ArrayList<Donation> getUserDonationsByToken(String token) {
        return null;
    }

    @Override
    public void testTr(){
        String ss = new String("ffff");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("Donation Transaction"); // Transaction name
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            User user = new User();
            user.setLogin("lol227");
            user.setPassword("lol227");
            usersRepository.save(user);
            System.out.println(22);
        }catch (NullPointerException e){
            transactionManager.rollback(status);
            throw e;
        }
        transactionManager.commit(status);
    }
}
