package com.example.footballanalyzer.repository;

import com.example.footballanalyzer.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
