package com.project.demo.dto;

import com.project.demo.model.Candidate;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Getter
@Setter
public class SkillDTO {
    private Long id;
    private String name;
    private List<Candidate> candidates;

    public SkillDTO(){}

}
