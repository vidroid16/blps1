package com.example.demo.Services.Implementations;

import com.example.demo.Controllers.MyResponse;
import com.example.demo.DataBase.DonationsDB.Donation;
import com.example.demo.DataBase.DonationsDB.DonationsRepository;
import com.example.demo.DataBase.ProjectsDB.Project;
import com.example.demo.DataBase.ProjectsDB.ProjectRepository;
import com.example.demo.DataBase.UsersDB.User;
import com.example.demo.DataBase.UsersDB.UsersRepository;
import com.example.demo.Services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DonationServiceImpl implements DonationService {
    private final UsersRepository usersRepository;
    private final ProjectRepository projectRepository;
    private final DonationsRepository donationsRepository;

    @Autowired
    public DonationServiceImpl(UsersRepository usersRepository, ProjectRepository projectRepository, DonationsRepository donationsRepository) {
        this.usersRepository = usersRepository;
        this.projectRepository = projectRepository;
        this.donationsRepository = donationsRepository;
    }

    @Override
    public boolean donate(String token, Long project_id, int sum) {
        try {
            User u = usersRepository.findByToken(token);
            Project p = projectRepository.findById(project_id).get();
            donationsRepository.save(new Donation(u,p,sum));
            projectRepository.donate(sum,project_id);
        }catch (NullPointerException e){
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Donation> getUserDonationsByToken(String token) {
        return null;
    }
}
