package org.example.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.example.userservice.dto.DiaryDTO;
import org.example.userservice.dto.UserDTO;


@Entity
@Data
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long DiaryId;

    private Long PlannerId;

    @Column(unique = true)
    private String name;

    private String password;

    private String roles;

    @Transient
    private DiaryDTO diary;

    @Transient
    private UserDTO user;
}
