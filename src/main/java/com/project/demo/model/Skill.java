package com.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "skills")
    @JsonIgnoreProperties("skills")
    private List<Candidate> candidates;
    public Skill(){}

}
