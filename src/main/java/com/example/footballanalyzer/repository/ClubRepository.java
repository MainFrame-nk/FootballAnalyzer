package com.example.footballanalyzer.repository;

import com.example.footballanalyzer.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findClubsByName(String name);
    //Club findCLubByName(String name);
}
