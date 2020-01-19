package com.project.demo.service;

import com.project.demo.model.Skill;
import java.util.List;

public interface SkillService {
    void save(Skill s);
    Skill findByName(String name);
    List<Skill> findAllSkills();
}
