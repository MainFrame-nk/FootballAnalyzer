package com.example.footballanalyzer.dto;

import com.example.footballanalyzer.enums.Role;
import com.example.footballanalyzer.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean active;
    private Set<Role> roles = new HashSet<>();
    private String password;
    private LocalDateTime dateOfCreated;

    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .dateOfCreated(user.getDateOfCreated())
                .build();
    }
}
