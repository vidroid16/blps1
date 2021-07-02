package com.example.demo.Controllers;

import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.ProjectsDB.ProjectRepository;
import com.example.demo.DataBase.UsersDB.User;
import com.example.demo.DataBase.UsersDB.UsersRepository;
import com.example.demo.Services.Implementations.DataServiceImpl;
import com.example.demo.Services.DataService;
import com.example.demo.Services.UserService;
import com.example.demo.socket.SocketMessage;
import io.swagger.v3.oas.annotations.Operation;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class DataController {
    private final DataServiceImpl dataService;
    private final UsersRepository userRepository;
    @Value("is_listen")
    private String isListenStr;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public DataController(DataServiceImpl dataService, UsersRepository userRepository) {
        this.dataService = dataService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Ищет проекты по имени")
    @GetMapping ("/search_project")

    public ArrayList<Project> test(String name){
        try{
            System.out.println(name);
            ArrayList<Project> pr = dataService.doSearch(name);
            return pr;
        }catch(Exception e){
            return null;
        }
    }

    @Operation(summary = "Create project")
    @PostMapping ("/add_project")
    public ResponseEntity addProject(@RequestBody Project project) {
        boolean isListen = Boolean.parseBoolean(isListenStr);
        if(!isListen) {
            try {
                dataService.addProject(project);
                SocketMessage message = new SocketMessage();
                message.setContent(String.valueOf(project.getId()));
                simpMessagingTemplate.convertAndSend("/queue/messages", message);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Operation(summary = "Change project card")
    @PostMapping ("/change_project_card")
    public ResponseEntity changeProjectCard(@RequestParam("project_id") Long projectId, @RequestParam("ncard") String nCardNumber) {
        boolean isListen = Boolean.parseBoolean(isListenStr);
        if(!isListen) {
            try {
                dataService.changeProjectCard(projectId, nCardNumber);
                SocketMessage message = new SocketMessage();
                message.setContent(String.valueOf(projectId));
                simpMessagingTemplate.convertAndSend("/queue/messages", message);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Operation(summary = "List users")
    @GetMapping ("/users")
    public ResponseEntity listUsers() {
        try {
            JSONArray array = new JSONArray(dataService.listUsers());
            return ResponseEntity.ok().body(array.toString());
        }catch (Exception e){
            return null;
        }
    }

    @Operation(summary = "Create user")
    @PostMapping ("/add_user")
    public ResponseEntity addUser(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password,
                                  @RequestParam(value = "first_name", required = false) String firstName, @RequestParam(value = "last_name", required = false) String lastName) {
        User user = userRepository.findByLogin(login).orElse(null);
        if (user != null)
            return ResponseEntity.badRequest().body("User has already existed!");
        user = new User();
        user.setPassword(new BCryptPasswordEncoder(12).encode(password));
        user.setLogin(login);
        if (firstName != null && !firstName.isEmpty())
            user.setFirstName(firstName);
        if (lastName != null && !lastName.isEmpty())
            user.setLastName(lastName);
        dataService.addUser(user);
        return ResponseEntity.ok().body("User was created");
    }
}
