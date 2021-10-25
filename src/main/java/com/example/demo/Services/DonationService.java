package com.example.demo.Services;

import com.example.demo.DataBase.DonationsDB.Donation;

import java.util.ArrayList;

public interface DonationService {
    int donate(String login, Long project_id, int sum, String cardNumber);
}
