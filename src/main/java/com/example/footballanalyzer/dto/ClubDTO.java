package com.example.footballanalyzer.dto;

import com.example.footballanalyzer.model.Club;
import com.example.footballanalyzer.model.Image;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Component
public class ClubDTO {
    private Long id;
    private String name;
    private String country;
    private String league;
   // private ImageDTO logotype = new ImageDTO();

    public ClubDTO(Long id) {
        this.id = id;
    }

//    public void addImageToClub(ImageDTO img) {
//        // img.setClub(this);
//        //logotype = img;
//    }

    public static ClubDTO toClubDTO(Club club) {
        return ClubDTO.builder()
                .id(club.getId())
                .name(club.getName())
                .country(club.getCountry())
                .league(club.getLeague())
                //.logotype(ImageDTO.toImageDTO(club.getLogotype()))
                .build();
    }
}
