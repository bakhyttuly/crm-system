package com.example.crmsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private Integer semester;
    private Double gpa;
}
