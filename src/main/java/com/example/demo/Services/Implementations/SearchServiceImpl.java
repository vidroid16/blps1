package com.example.demo.Services.Implementations;

import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.ProjectsDB.ProjectRepository;
import com.example.demo.Services.SearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class SearchServiceImpl implements SearchService {
    private final ProjectRepository projectRepository;

    public SearchServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ArrayList<Project> doSearch(String name) {
        ArrayList<Project> pr = projectRepository.findAllByNameContaining(name);
        return pr;
    }
}
