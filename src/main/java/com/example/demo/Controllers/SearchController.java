package com.example.demo.Controllers;

import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.ProjectsDB.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SearchController {
    @Autowired
    ProjectRepository projectRepository;

    @Operation(summary = "Ищет проекты по имени")
    @GetMapping ("/search")
    public ArrayList<Project> test(String name){
        System.out.println(name);
        ArrayList<Project> pr = projectRepository.findAllByNameContaining(name);
        return pr;
    }
}
