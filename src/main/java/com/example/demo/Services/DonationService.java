package com.example.demo.Services;

import com.example.demo.DataBase.DonationsDB.Donation;

import java.util.ArrayList;

public interface DonationService {
    boolean donate(String login, Long project_id, int sum);
    ArrayList<Donation> getUserDonationsByToken(String token);

}
