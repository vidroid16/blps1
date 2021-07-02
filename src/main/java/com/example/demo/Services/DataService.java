package com.example.demo.Services;

import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.UsersDB.User;

import java.util.ArrayList;

public interface DataService {
    ArrayList<Project> doSearch(String name);
    void addUser(User user);
    void addProject(Project project);
    ArrayList<User> listUsers();
    void moderate(Long project);
    void changeProjectCard(Long projectId, String nCardNum);
}
