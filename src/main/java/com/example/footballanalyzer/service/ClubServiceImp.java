package com.example.footballanalyzer.service;

import com.example.footballanalyzer.dto.ClubDTO;
import com.example.footballanalyzer.model.Club;
import com.example.footballanalyzer.model.Image;
import com.example.footballanalyzer.model.User;
import com.example.footballanalyzer.repository.ClubRepository;
import com.example.footballanalyzer.repository.ImageRepository;
import com.example.footballanalyzer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClubServiceImp implements ClubService {
    private final ClubRepository clubRepository;
    //private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    @Override
    public List<ClubDTO> getAllClubs() {
        return clubRepository.findAll().stream().map(Club::toClubDTO).sorted(Comparator.comparing(ClubDTO::getId)).collect(Collectors.toList());
    }

    @Override
    public List<ClubDTO> getClubsByName(String name) {
        return clubRepository.findClubsByName(name).stream().map(Club::toClubDTO).sorted(Comparator.comparing(ClubDTO::getName)).collect(Collectors.toList());
    }

//    @Override
//    public Club getClubByName(String name) {
//        return clubRepository.findCLubByName(name);
//    }

    @Transactional
    public ClubDTO createClub(ClubDTO clubDTO) {
//        Image logo;
//        if (file.getSize() != 0) {
//            logo = toImageEntity(file);
//            clubDTO.addImageToClub(logo.toImageDTO());
//        }
        log.info("Saving new Club. Name: {}.", clubDTO.getName());
        Club club = Club.builder()
                .name(clubDTO.getName())
                .country(clubDTO.getCountry())
                .league(clubDTO.getLeague())
                //.logotype(imageRepository.findById(clubDTO.getLogotype().getId()).orElse(null))
                .build();
        clubRepository.save(club);
        return clubDTO;
    }

    @Transactional
    @Override
    public void deleteClub(Long id) {
        Optional<Club> club = clubRepository.findById(id);
        if (club.isPresent()) {
            log.info("Deleted club. Name: {}.", club.get().getName());
            clubRepository.deleteById(id);
        } else {
            log.error("Error! Club not found!");
        }
    }

    @Override
    public Club getClubById(Long id) {
        return clubRepository.findById(id).orElse(null);
    }

//    public User getUserByPrincipal(Principal principal) {
//        if (principal == null) return new User();
//        return userRepository.findByEmail(principal.getName());
//    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image img = new Image();
        img.setName(file.getName());
        img.setOriginalFileName(file.getOriginalFilename());
        img.setContentType(file.getContentType());
        img.setSize(file.getSize());
        img.setBytes(file.getBytes());
        return img;
    }

    @Transactional
    @Override
    public ClubDTO updateClub(ClubDTO clubDTO, Long id) {
        //Image logo;
        Club club = clubRepository
                .findById(id)
                .orElse(null);
        if (club != null) {
            club.setName(clubDTO.getName());
            club.setCountry(clubDTO.getCountry());
            club.setLeague(clubDTO.getLeague());

//            if (file.getSize() != 0) {
//                logo = toImageEntity(file);
//                clubDTO.addImageToClub(logo.toImageDTO());
//            }
//            club.setLogotype(imageRepository.findById(clubDTO.getLogotype().getId()).orElse(club.getLogotype()));
            clubRepository.save(club);
        }
        return clubDTO;
    }

}
