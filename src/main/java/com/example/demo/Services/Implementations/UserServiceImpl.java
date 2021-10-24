package com.example.demo.Services.Implementations;

import com.example.demo.DataBase.UsersDB.User;
import com.example.demo.DataBase.UsersDB.UsersRepository;
import com.example.demo.Services.UserService;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashMap;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private UsersRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private final PlatformTransactionManager transactionManager;

    public UserServiceImpl(AuthenticationManager authenticationManager, UsersRepository userRepository, JwtTokenProvider jwtTokenProvider, PlatformTransactionManager transactionManager) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.transactionManager = transactionManager;
    }

    @Override
    public String login(String username, String password) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("Donation Transaction"); // Transaction name
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        String tok;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
            String token = jwtTokenProvider.createToken(username, user.getRole().name());
            tok = token;
        } catch (AuthenticationException e) {
            transactionManager.rollback(status);
            //return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
            return "bad";
        }
        transactionManager.commit(status);
        return tok;
    }
}
