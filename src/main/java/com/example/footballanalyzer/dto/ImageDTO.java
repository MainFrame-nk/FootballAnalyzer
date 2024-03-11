package com.example.footballanalyzer.dto;

import com.example.footballanalyzer.model.Club;
import com.example.footballanalyzer.model.Image;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Component
public class ImageDTO {
    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    private byte[] bytes;

    public ImageDTO(Long id) {
        this.id = id;
    }

    public static ImageDTO toImageDTO(Image image) {
        return ImageDTO.builder()
                .id(image.getId())
                .name(image.getName())
                .originalFileName(image.getOriginalFileName())
                .size(image.getSize())
                .contentType(image.getContentType())
                .bytes(image.getBytes())
                .build();
    }
}
