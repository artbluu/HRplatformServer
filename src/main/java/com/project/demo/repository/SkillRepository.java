package com.project.demo.repository;

import com.project.demo.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Skill findByName(String name);
}
