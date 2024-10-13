package com.Suresh.SpringBoot_MicroService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String experience;

}