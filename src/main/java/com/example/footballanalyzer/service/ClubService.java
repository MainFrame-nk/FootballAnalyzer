package com.example.footballanalyzer.service;

import com.example.footballanalyzer.dto.ClubDTO;
import com.example.footballanalyzer.model.Club;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ClubService {
    List<ClubDTO> getAllClubs();
    List<ClubDTO> getClubsByName(String name);
    //Club getClubByName(String name);
    ClubDTO createClub(ClubDTO clubDTO);
    Club getClubById(Long id);
    void deleteClub(Long id);
    ClubDTO updateClub(ClubDTO clubDTO, Long id);
}
