package com.example.demo.DataBase.UsersDB;


import com.example.demo.DataBase.ProjectsDB.Project;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@JsonIgnoreProperties("password")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;
    private String password;
    private String token;

    public User(){}
//    @ManyToMany
//    @JoinTable(name = "donations",
//            joinColumns = { @JoinColumn(name = "user_id") },
//            inverseJoinColumns = { @JoinColumn(name = "project_id") })
//    private Set<Project> projectSet = new HashSet<Project>();
    @Override
    public String toString() {
        return "Login:"+login+" Password:"+password;
    }

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String token(){
        this.token = UUID.randomUUID().toString();
        return this.token;
    }

    public String getLogin(){
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}