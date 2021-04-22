package com.example.demo.Services;

import com.example.demo.DataBase.ProjectsDB.Project;

import java.util.ArrayList;

public interface SearchService {
    ArrayList<Project> doSearch(String name);
}
