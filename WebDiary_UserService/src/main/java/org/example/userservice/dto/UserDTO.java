package org.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long diaryId;
    private Long plannerId;
    private String name;
    private String password;
    private String roles;
}