package com.example.demo.DataBase.DonationsDB;

import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.UsersDB.User;
import org.springframework.data.repository.CrudRepository;

public interface DonationsRepository extends CrudRepository<Donation, DonationID> {
    Donation findByUser(User user);
    Donation findByProject(Project project);
}
