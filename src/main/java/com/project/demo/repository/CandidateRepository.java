package com.project.demo.repository;

import com.project.demo.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Candidate findByEmail(String email);

}
