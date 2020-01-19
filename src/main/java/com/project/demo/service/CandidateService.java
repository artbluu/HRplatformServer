package com.project.demo.service;
import com.project.demo.model.Candidate;
import java.util.List;

public interface CandidateService {
    List<Candidate> findAllCandidates();
    Candidate findById(Long id);
    Candidate findByEmail(String username);
    List<Candidate> findCandidatesByName(String name);
    List<Candidate> findCandidatesBySkills(String skill);
    void save(Candidate c);
    void delete(Candidate c);
}
