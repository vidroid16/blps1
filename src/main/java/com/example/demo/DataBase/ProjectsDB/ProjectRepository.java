package com.example.demo.DataBase.ProjectsDB;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ProjectRepository extends CrudRepository<Project,Long> {
    ArrayList<Project> findAllByNameContaining(String name);
    Project findByName(String name);
    @Modifying
    @Query("update Project p set cur_sum = cur_sum + :sum WHERE id = :Id")
    void donate(@Param("sum") int sum, @Param("Id") Long id);
    //Project findById(Long id);
}
