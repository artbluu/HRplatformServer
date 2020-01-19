package com.project.demo.service.implementation;

import com.project.demo.model.Skill;
import com.project.demo.repository.SkillRepository;
import com.project.demo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public void save(Skill s) {
        this.skillRepository.save(s);
    }

    @Override
    public Skill findByName(String name) {
        return this.skillRepository.findByName(name);
    }

    @Override
    public List<Skill> findAllSkills() {
        return this.skillRepository.findAll();
    }
}
