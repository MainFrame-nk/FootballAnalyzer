package com.example.footballanalyzer.model;

import javax.persistence.*;

import com.example.footballanalyzer.dto.ImageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "originalFileName")
    private String originalFileName;
    @Column(name = "size")
    private Long size;
    @Column(name = "contentType")
    private String contentType;
    @Lob
    @Column(name = "bytes", columnDefinition = "longblob")
    private byte[] bytes;

//    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    private Club club;

    public ImageDTO toImageDTO() {
        return ImageDTO.toImageDTO(this);
    }
}
