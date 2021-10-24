package com.example.demo.DataBase.DonationsDB;

import org.springframework.web.bind.annotation.RequestBody;

public class DonationBody{
    private String token;
    private Long project_id;
    private int sum;

    public DonationBody(){};
    public DonationBody(String token, Long project_id, int sum) {
        this.token = token;
        this.project_id = project_id;
        this.sum = sum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
