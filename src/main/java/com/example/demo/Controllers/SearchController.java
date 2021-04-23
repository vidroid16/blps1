package com.example.demo.Controllers;

import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.ProjectsDB.ProjectRepository;
import com.example.demo.DataBase.UsersDB.User;
import com.example.demo.Services.Implementations.SearchServiceImpl;
import com.example.demo.Services.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class SearchController {
    private final SearchServiceImpl searchService;

    @Autowired
    public SearchController(SearchServiceImpl searchService) {
        this.searchService = searchService;
    }

    @Operation(summary = "Ищет проекты по имени")
    @GetMapping ("/search")
    public ArrayList<Project> test(String name){
        System.out.println(name);
        ArrayList<Project> pr = searchService.doSearch(name);
        return pr;
    }

    @Operation(summary = "Create project")
    @PostMapping ("/add_project")
    public ResponseEntity addProject(@RequestBody Project project) {
        searchService.addProject(project);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Create user")
    @GetMapping ("/users")
    public ResponseEntity listUsers() {
        JSONArray array = new JSONArray(searchService.listUsers());
        return ResponseEntity.ok().body(array.toString());
    }

    @Operation(summary = "Create user")
    @PostMapping ("/add_user")
    public ResponseEntity addUser(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password) {
        searchService.addUser(new User(login, password));
        return ResponseEntity.ok().build();
    }
}
