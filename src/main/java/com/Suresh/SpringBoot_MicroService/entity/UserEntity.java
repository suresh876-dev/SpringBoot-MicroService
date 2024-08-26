package com.Suresh.SpringBoot_MicroService.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userentity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username",nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles;
}
