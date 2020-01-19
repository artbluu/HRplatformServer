package com.project.demo.service.implementation;

import com.project.demo.dto.SkillDTO;
import com.project.demo.model.Candidate;
import com.project.demo.model.Skill;
import com.project.demo.repository.CandidateRepository;
import com.project.demo.repository.SkillRepository;
import com.project.demo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Candidate> findAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate findById(Long id) {
        Candidate c = candidateRepository.findById(id).orElseGet(null);

        return c;
    }

    @Override
    public Candidate findByEmail(String email) {
        Candidate c = candidateRepository.findByEmail(email);
        return c;
    }

    @Override
    public List<Candidate> findCandidatesByName(String name) {
        List<Candidate> candidates = this.candidateRepository.findAll();
        List<Candidate> foundCandidates = new ArrayList<Candidate>();
        for(Candidate c : candidates){
            if(c.getName().toLowerCase().contains(name.toLowerCase())){
                foundCandidates.add(c);
            }
        }
        return foundCandidates;
    }

    @Override
    public List<Candidate> findCandidatesBySkills(String name) {

        List<Candidate> candidates = this.candidateRepository.findAll();

        String[] skills = name.split("@");

        List<Candidate> foundCandidates = new ArrayList<Candidate>();
        for(String skillName : skills){
            Skill s = this.skillRepository.findByName(skillName);
            if(s != null)
            for(Candidate c : candidates) {

                if (c.getSkills().contains(s)) {
                    foundCandidates.add(c);
                    candidates.remove(c);
                    break;
                }
            }

        }



        return foundCandidates;
    }

    @Override
    public void save(Candidate c) {
        this.candidateRepository.save(c);
    }

    @Override
    public void delete(Candidate c) {
        c.setSkills(null);
        this.candidateRepository.save(c);
        this.candidateRepository.delete(c);
    }
}
