package com.example.demo.DataBase.DonationsDB;

import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.UsersDB.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;


    private int sum;
    private Timestamp donation_time;

    public Donation(User user, Project project, int sum) {
        this.user = user;
        this.project = project;
        this.sum = sum;
        this.donation_time = new Timestamp(System.currentTimeMillis());
    }

    public Donation(){
       this.donation_time = new Timestamp(System.currentTimeMillis());
    }

}