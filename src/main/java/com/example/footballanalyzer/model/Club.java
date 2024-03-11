package com.example.footballanalyzer.model;

import javax.persistence.*;

import com.example.footballanalyzer.dto.ClubDTO;
import com.example.footballanalyzer.dto.ImageDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Entity
@Table(name = "clubs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "league")
    private String league;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "image_id")
//    private Image logotype;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinTable(name = "table_images",
//            joinColumns = @JoinColumn(name = "object_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"}) // Хорошо бы сделать обработку
//    private Image logotype = new Image();

//    public void toImage(ImageDTO img) {
//       // img.setClub(this);
//        logotype = new Image(img);
//    }

    public ClubDTO toClubDTO() {
        return ClubDTO.toClubDTO(this);
    }
}
