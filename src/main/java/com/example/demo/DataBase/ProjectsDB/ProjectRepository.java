package com.example.demo.DataBase.ProjectsDB;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface ProjectRepository extends CrudRepository<Project,Long> {
    ArrayList<Project> findAllByNameContaining(String name);
    Project findByName(String name);

    @Modifying
    @Query("update Project p set cur_sum = cur_sum + :sum WHERE id = :Id")
    void donate(@Param("sum") int sum, @Param("Id") Long id);

    @Modifying
    @Query("update Project p set card = :cardNum WHERE id = :Id")
    void changeCard(@Param("cardNum") String cardNum, @Param("Id") Long id);
}
