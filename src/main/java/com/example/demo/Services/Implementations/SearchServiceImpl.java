package com.example.demo.Services.Implementations;

import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.ProjectsDB.ProjectRepository;
import com.example.demo.DataBase.UsersDB.User;
import com.example.demo.DataBase.UsersDB.UsersRepository;
import com.example.demo.Services.SearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class SearchServiceImpl implements SearchService {
    private final ProjectRepository projectRepository;
    private final UsersRepository usersRepository;

    public SearchServiceImpl(ProjectRepository projectRepository, UsersRepository usersRepository) {
        this.projectRepository = projectRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public void addUser(User user) {
        user.token();
        usersRepository.save(user);
    }

    @Override
    public ArrayList<User> listUsers() {
        return (ArrayList<User>) usersRepository.findAll();
    }

    @Override
    public void addProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public ArrayList<Project> doSearch(String name) {
        ArrayList<Project> pr = projectRepository.findAllByNameContaining(name);
        return pr;
    }
}
