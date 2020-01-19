package com.project.demo.dto;

import com.project.demo.model.Skill;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.Set;
@Getter
@Setter
public class CandidateDTO {

    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private List<Skill> skills;

    public CandidateDTO(){}

}
