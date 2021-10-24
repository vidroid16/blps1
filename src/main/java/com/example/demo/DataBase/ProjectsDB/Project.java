package com.example.demo.DataBase.ProjectsDB;

import com.example.demo.DataBase.UsersDB.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {
        @Id
        @GeneratedValue
        private Long id;
        private String name;
        private int need_sum;
        private int cur_sum;

        public Project(){}

        public Project(String name, int need_sum, int cur_sum) {
                this.name = name;
                this.need_sum = need_sum;
                this.cur_sum = cur_sum;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getNeed_sum() {
                return need_sum;
        }

        public void setNeed_sum(int need_sum) {
                this.need_sum = need_sum;
        }

        public int getCur_sum() {
                return cur_sum;
        }

        public void setCur_sum(int cur_sum) {
                this.cur_sum = cur_sum;
        }
        //        @ManyToMany
//        @JoinTable(name = "donations",
//                joinColumns = { @JoinColumn(name = "project_id") },
//                inverseJoinColumns = { @JoinColumn(name = "user_id") })
//        private Set<User> users = new HashSet<User>();

}
